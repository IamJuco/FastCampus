package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import com.example.myapplication.databinding.ActivityAddBinding
import com.google.android.material.chip.Chip

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private var originWord: Word? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uiSetting()
        initViews()

        binding.btnAdd.setOnClickListener {
            if (originWord == null) {
                add()
            } else {
                edit()
            }
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

        originWord = intent.getParcelableExtra<Word>("originWord")
        originWord?.let { word ->
            binding.etTextInput.setText(word.text)
            binding.etMeanTextInput.setText(word.mean)
            val selectedChip =
                binding.chipTypeGroup.children.firstOrNull { (it as Chip).text == word.type } as? Chip
            selectedChip?.isChecked = true
        }
    }

    private fun createChip(text: String): Chip {
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
                Toast.makeText(this, "저장을 완료 했습니다.", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent().putExtra("isUpdated", true)
            setResult(RESULT_OK, intent)
            finish()
        }.start() // thread를 시작해 줘야함
    }

    private fun edit() {
        val text = binding.etTextInput.text.toString()
        val mean = binding.etMeanTextInput.text.toString()
        val type = findViewById<Chip>(binding.chipTypeGroup.checkedChipId).text.toString()
        val editWord = originWord?.copy(text = text, mean = mean, type = type)

        Thread {
            editWord?.let {
                AppDatabase.getInstance(this)?.wordDao()?.update(editWord)
                val intent = Intent().putExtra("editWord", editWord)
                setResult(RESULT_OK, intent)
                runOnUiThread {
                    Toast.makeText(this, "수정을 완료 했습니다.", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        }.start()
    }
}