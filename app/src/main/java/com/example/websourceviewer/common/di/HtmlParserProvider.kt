package com.example.websourceviewer.common.di

import com.example.websourceviewer.ui.common.CustomHtmlParser
import com.example.websourceviewer.ui.common.HtmlParser
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HtmlParserProvider {

    @Singleton
    @Binds
    abstract fun bindHtmlParser(customHtmlParser: CustomHtmlParser): HtmlParser
}