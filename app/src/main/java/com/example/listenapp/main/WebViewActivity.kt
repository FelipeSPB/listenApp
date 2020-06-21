package com.example.listenapp.main

import android.os.Bundle
import base.ActBind
import com.example.listenapp.databinding.WebviewBinding
import custom.onClick
import custom.viewBind

class WebViewActivity : ActBind<WebviewBinding>() {
    override val binding: WebviewBinding by viewBind()
    lateinit var url: String

    override fun Bundle.onExtras() {
        url = getString("URL") ?: ""
    }
    override fun WebviewBinding.onBoundView() {
        backArrow.onClick {onBackPressed()}
        webview.loadUrl(url)
    }



}