package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityAddBinding
import com.google.android.material.chip.Chip

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uiSetting()
        initViews()

        binding.btnAdd.setOnClickListener {
            add()
        }

    }

    private fun uiSetting() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemGestures())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViews() {
        val types = listOf("명사", "동사", "대명사", "형용사", "부사", "감탄사", "전치사", "접속사")
        binding.chipTypeGroup.apply {
            types.forEach { text ->
                addView(createChip(text))
            }
        }
    }

    private fun createChip(text: String) : Chip {
        return Chip(this).apply {
            setText(text)
            isCheckable = true
            isClickable = true
        }
    }

    private fun add() {
        val text = binding.etTextInput.text.toString()
        val mean = binding.etMeanTextInput.text.toString()
        val type = findViewById<Chip>(binding.chipTypeGroup.checkedChipId).text.toString()
        val word = Word(text, mean, type)
        Thread { // 메인이 아닌 workThread 에서 작업을 해줘야함
            AppDatabase.getInstance(this)?.wordDao()?.insert(word)
            runOnUiThread { // ui 작업은 여기서 해야함
                Toast.makeText(this,"저장을 완료 했습니다.", Toast.LENGTH_SHORT).show()
            }
            finish()
        }.start() // thread를 시작해 줘야함
    }
}