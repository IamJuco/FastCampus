package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uiSetting()
        initRecyclerView()

        binding.fabAdd.setOnClickListener {
            Intent(this, AddActivity::class.java).let {
                startActivity(it)
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

    private fun initRecyclerView() {
        wordAdapter = WordAdapter(mutableListOf(), this)
        binding.rvWord.apply {
            adapter = wordAdapter
            // 현재 apply를 열었기 때문에 this대신 applicationContext 넣었음, apply의 this는 현재 recyclerview임
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            // DividerItemDecoration = 구분 선을 아이템 아래에 만들어 줌 ( 커스텀 가능 )
            val dividerItemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onClick(word: Word) {
        Toast.makeText(this, "${word.text} 가 클릭 됐습니다.", Toast.LENGTH_SHORT).show()
    }
}