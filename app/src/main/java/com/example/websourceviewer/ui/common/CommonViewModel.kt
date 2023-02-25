package com.example.websourceviewer.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(private val customHtmlParser: CustomHtmlParser) : ViewModel() {

    private val scope = viewModelScope
    private val state = MutableStateFlow(State())

    val stateFlow = state.asStateFlow()

    init {
        customHtmlParser.htmlBody.onEach {
            with(state) {
                emit(value.copy(html = it))
            }
        }.launchIn(scope)
    }

    fun setUrlToLoad(url: String) {
        with(state) {
            scope.launch { emit(value.copy(urlToLoad = url)) }
        }
        getHtmlBody()
    }

    private fun getHtmlBody() {
        with (state) {
            scope.launch(Dispatchers.IO) { customHtmlParser.requestHtmlBodyFor(value.urlToLoad) }
        }
    }

    data class State(
        val urlToLoad: String = "",
        val html: String = ""
    )
}