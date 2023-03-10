package com.example.websourceviewer.ui.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class CustomHtmlParser @Inject constructor(private val okHttpClient: OkHttpClient) : HtmlParser {
    private val htmlStateFlow = MutableStateFlow("Loading")
    override val htmlBody: StateFlow<String> = htmlStateFlow.asStateFlow()

    override suspend fun requestHtmlBodyFor(url: String) {
        val request: Request = Request.Builder()
            .url(url)
            .build()

        okHttpClient
            .newCall(request)
            .enqueue(
                object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        htmlStateFlow.tryEmit("Error: $e")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        htmlStateFlow.tryEmit(response.body?.string() ?: "Invalid body")
                    }
                }
            )
    }
}