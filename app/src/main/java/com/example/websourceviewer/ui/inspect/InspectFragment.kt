package com.example.websourceviewer.ui.inspect

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.websourceviewer.R
import com.example.websourceviewer.databinding.FragmentInspectBinding
import com.example.websourceviewer.ui.common.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class InspectFragment : Fragment(R.layout.fragment_inspect) {

    private lateinit var binding: FragmentInspectBinding
    private val viewModel: CommonViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInspectBinding.bind(view)
        setUpWebView()
        viewModel.stateFlow.onEach {
            loadUrlToWebView(it.urlToLoad)
            showHtmlBody(it.html)
        }.launchIn(lifecycleScope)


    }

    private fun setUpWebView() {
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean = false
        }
    }

    private fun loadUrlToWebView(url: String) {
        binding.webView.loadUrl(url)
    }

    private fun showHtmlBody(body: String) {
        binding.textViewHtml.text = body
    }
}