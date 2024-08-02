package com.example.chapter1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.chapter1.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int) : Fragment() {
    private lateinit var binding: FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.webView.webViewClient = WebToonWebViewClient(binding.progressBar) { url ->
            activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)?.edit {
                putString("tab$position", url)
            }
        }
        // XSS 취약성 ( 외부로부터 공격 받을 수 있음, 무시 해도 됌 )
        binding.webView.settings.javaScriptEnabled = true

        binding.webView.loadUrl("https://comic.naver.com/")

        binding.btnBackToLast.setOnClickListener {
            val sharedPreference = activity?.getSharedPreferences("WEB_HISTORY", AppCompatActivity.MODE_PRIVATE)
            val url = sharedPreference?.getString("tab$position", "")
            if (url.isNullOrEmpty()) {
                Toast.makeText(context, "마지막 저장 시점이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.webView.loadUrl(url)
            }
        }
    }

    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }
}