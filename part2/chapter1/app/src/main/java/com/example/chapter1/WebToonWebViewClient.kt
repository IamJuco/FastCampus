package com.example.chapter1

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class WebToonWebViewClient(
    private val progressBar: ProgressBar,
    private val saveData: (String) -> Unit
) :
    WebViewClient() {

    // return 값이 true = view가 로딩을 안함 ( 로딩이 되기직전에 불림 )
    // false = view를 로드함
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (request != null && request.url.toString().contains("https://comic.naver.com/")) {
            saveData(request.url.toString())
        }
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        progressBar.visibility = View.GONE
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        progressBar.visibility = View.VISIBLE

    }
}

/*
      // 에러가 생겼을때 ( 에러페이지를 띄워주거나 하는 처리 )
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)

    }

 */

/*


    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return false
    }

 */