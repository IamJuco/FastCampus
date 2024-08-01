package com.example.chapter1

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        uiSetting()

        val container = binding.fragmentContainer

        binding.btn1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, WebViewFragment())
                commit()
            }
        }

        binding.btn2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, WebViewFragment())
                commit()
            }
        }
    }

    private fun uiSetting() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}