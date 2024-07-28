package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter
    private var selectedWord: Word? = null
    private val updateAddWordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val isUpdated = result.data?.getBooleanExtra("isUpdated", false) ?: false
        if (result.resultCode == RESULT_OK && isUpdated) {
            updateAddWord()
        }
    }

    private val updateEditWordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val editWord = result.data?.getParcelableExtra<Word>("editWord")
        if (result.resultCode == RESULT_OK && editWord != null) {
            updatedEditWord(editWord)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uiSetting()
        initRecyclerView()

        binding.fabAdd.setOnClickListener {
            Intent(this, AddActivity::class.java).let {
                updateAddWordResult.launch(it)
            }
        }

        binding.ivDelete.setOnClickListener {
            delete()
        }

        binding.ivEdit.setOnClickListener {
            edit()
        }

    }

    private fun uiSetting() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initRecyclerView() {
        wordAdapter = WordAdapter(mutableListOf(), this)
        binding.rvWord.apply {
            adapter = wordAdapter
            // 현재 apply를 열었기 때문에 this대신 applicationContext 넣었음, apply의 this는 현재 recyclerview임
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            // DividerItemDecoration = 구분 선을 아이템 아래에 만들어 줌 ( 커스텀 가능 )
            val dividerItemDecoration =
                DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
        Thread {
            val list = AppDatabase.getInstance(this)?.wordDao()?.getAll() ?: emptyList()
            Thread.sleep(1000)
            wordAdapter.list.addAll(list)
            runOnUiThread { // view에 관련된건 runOnUiThread 에다가 넣어줘야함
                wordAdapter.notifyDataSetChanged()
            }
        }.start()
    }

    private fun updateAddWord() {
        Thread {
            AppDatabase.getInstance(this)?.wordDao()?.getLatestWord()?.let { word ->
                wordAdapter.list.add(0, word)
                runOnUiThread {
                    wordAdapter.notifyDataSetChanged()
                }
            }

        }.start()
    }

    private fun updatedEditWord(word: Word) {
        val index = wordAdapter.list.indexOfFirst { it.id == word.id }
        wordAdapter.list[index] = word
        runOnUiThread {
            selectedWord = word
            wordAdapter.notifyItemChanged(index)
            binding.tvText.text = word.text
            binding.tvMean.text = word.mean
        }
    }

    private fun delete() {
        if (selectedWord == null) return
        Thread {
            selectedWord?.let { word ->
                AppDatabase.getInstance(this)?.wordDao()?.delete(word)
                runOnUiThread {
                    wordAdapter.list.remove(word)
                    wordAdapter.notifyDataSetChanged()
                    binding.tvText.text = ""
                    binding.tvMean.text = ""
                    Toast.makeText(this, "삭제가 완료 됐습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

    private fun edit() {
        if (selectedWord == null) return
        val intent = Intent(this, AddActivity::class.java).putExtra("originWord", selectedWord)
        updateEditWordResult.launch(intent)
    }

    override fun onClick(word: Word) {
        selectedWord = word
        binding.tvText.text = word.text
        binding.tvMean.text = word.mean
    }
}