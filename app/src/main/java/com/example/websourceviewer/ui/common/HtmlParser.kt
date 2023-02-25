package com.example.websourceviewer.ui.common

import kotlinx.coroutines.flow.StateFlow

interface HtmlParser {
    val htmlBody: StateFlow<String>
    suspend fun requestHtmlBodyFor(url: String)
}