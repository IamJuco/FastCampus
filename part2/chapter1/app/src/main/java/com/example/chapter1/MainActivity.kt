package com.example.chapter1

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        uiSetting()

        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        // XSS 취약성 ( 외부로부터 공격 받을 수 있음, 무시 해도 됌 )
        webView.settings.javaScriptEnabled = true

        webView.loadUrl("https://google.com")
    }

    private fun uiSetting() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}