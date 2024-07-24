package com.example.chapter6

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.chapter6.databinding.ActivityMainBinding
import com.example.chapter6.databinding.DialogCountdownSettingBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countdownSecond = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        uiSetting()

        binding.tvCountdown.setOnClickListener {
            showCountdownSettingDialog()
        }

        binding.fabStart.setOnClickListener {
            start()
            binding.fabStart.isVisible = false
            binding.fabStop.isVisible = false
            binding.fabPause.isVisible = true
            binding.fabLap.isVisible = true
        }

        binding.fabStop.setOnClickListener {
            showAlertDialog()
        }

        binding.fabPause.setOnClickListener {
            pause()
            binding.fabStart.isVisible = true
            binding.fabStop.isVisible = true
            binding.fabPause.isVisible = false
            binding.fabLap.isVisible = false

        }

        binding.fabLap.setOnClickListener {
            lab()

        }


    }

    private fun uiSetting() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun start() {

    }

    private fun stop() {
        binding.fabStart.isVisible = true
        binding.fabStop.isVisible = true
        binding.fabPause.isVisible = false
        binding.fabLap.isVisible = false
    }

    private fun pause() {

    }

    private fun lab() {

    }

    private fun showCountdownSettingDialog() {
        AlertDialog.Builder(this).apply {
            val dialogBinding = DialogCountdownSettingBinding.inflate(layoutInflater)
            with(dialogBinding.numberPickerCountdownSecond) {
                maxValue = 20
                minValue = 0
                value = countdownSecond
            }

            setTitle("카운트다운 설정")
            setView(dialogBinding.root)
            setPositiveButton("확인") { _, _ ->
                countdownSecond = dialogBinding.numberPickerCountdownSecond.value
                binding.tvCountdown.text = String.format("%02d", countdownSecond) // 05초, 08초 식으로 표현, "%03d"면 011초
            }
            setNegativeButton("취소", null)
        }.show()
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("종료 하시겠습니까?")
            setPositiveButton("네") { _, _ ->
                stop()
            }
            setNegativeButton("아니요", null)
        }.show()
    }
}