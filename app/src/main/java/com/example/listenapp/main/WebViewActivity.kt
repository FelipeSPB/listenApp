package com.example.listenapp.main

import android.os.Bundle
import base.ActBind
import com.example.listenapp.databinding.WebviewBinding
import custom.loadInApp
import custom.onClick
import custom.viewBind
import kotlinx.android.synthetic.main.webview.*

class WebViewActivity : ActBind<WebviewBinding>() {
    override val binding: WebviewBinding by viewBind()
    lateinit var url: String

    override fun Bundle.onExtras() {
        url = getString("URL") ?: ""
    }
    override fun WebviewBinding.onBoundView() {
        backArrow.onClick {super.onBackPressed()}
        webview.loadUrl(url)
        webview.loadInApp()
    }
    override fun onBackPressed() {
        if (webview.canGoBack()) webview.goBack() else super.onBackPressed()
    }
}



