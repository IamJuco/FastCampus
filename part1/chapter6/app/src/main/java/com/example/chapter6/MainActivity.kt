package com.example.chapter6

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.example.chapter6.databinding.ActivityMainBinding
import com.example.chapter6.databinding.DialogCountdownSettingBinding
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countdownSecond = 0
    private var currentDeciSecond = 0
    private var timer: Timer? = null
    private var currentCountdownDeciSecond = currentDeciSecond * 10

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
            lap()
        }

        initCountdownViews()
    }

    private fun uiSetting() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initCountdownViews() {
        binding.tvCountdown.text = String.format("%02d", countdownSecond)
        binding.progressBarCountdown.progress = 100
    }

    private fun start() {
        // timer도 Thread를 만드는것과 동일함
        // period = 1초는 1000ms, 0.1초 = 100
        timer = timer(initialDelay = 0, period = 100) {
            if (currentCountdownDeciSecond == 0) {
                currentDeciSecond += 1

                val minutes = currentDeciSecond.div(10) / 60
                val second = currentDeciSecond.div(10) % 60
                val deciSeconds = currentDeciSecond % 10

                runOnUiThread {
                    binding.tvTime.text =
                        String.format("%02d:%02d", minutes, second)
                    binding.tvTick.text = deciSeconds.toString()

                    binding.groupCountdown.isVisible = false
                }
            } else {
                currentCountdownDeciSecond -= 1
                val seconds = currentCountdownDeciSecond / 10
                val progress = (currentCountdownDeciSecond / (countdownSecond * 10f)) * 100

                binding.root.post {
                    binding.tvCountdown.text = String.format("%02d", seconds)
                    binding.progressBarCountdown.progress = progress.toInt()
                }
            }
            if (currentDeciSecond == 0 && currentCountdownDeciSecond < 31 && currentCountdownDeciSecond % 10 == 0) {
                val toneType = if (currentCountdownDeciSecond == 0) ToneGenerator.TONE_CDMA_HIGH_L else ToneGenerator.TONE_CDMA_ANSWER
                ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME)
                    .startTone(toneType, 100)
            }
        }
    }

    private fun stop() {
        binding.fabStart.isVisible = true
        binding.fabStop.isVisible = true
        binding.fabPause.isVisible = false
        binding.fabLap.isVisible = false

        currentDeciSecond = 0
        currentCountdownDeciSecond = countdownSecond * 10 // stop 한 뒤 다시 Start 하면, 그때도 카운트 다운 실행 되도록

        binding.tvTime.text = "00:00"
        binding.tvTick.text = "0"

        binding.groupCountdown.isVisible = true
        initCountdownViews()
        binding.linearLayoutLabContainer.removeAllViews()
    }

    private fun pause() {
        timer?.cancel()
        timer = null
    }

    private fun lap() {
        if (currentDeciSecond == 0) return
        val container = binding.linearLayoutLabContainer
        TextView(this).apply {
            textSize = 20f // Size는 float로 설정해야함
            gravity = Gravity.CENTER
            val minutes = currentDeciSecond.div(10) / 60
            val seconds = currentDeciSecond.div(10) % 60
            val deciSeconds = currentDeciSecond % 10

            text = "${container.childCount.inc()}. " + String.format(
                "%02d:%02d %01d",
                minutes,
                seconds,
                deciSeconds
            )
            setPadding(30)
        }.let { labTextView ->
            container.addView(labTextView, 0)
        }

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
                currentCountdownDeciSecond = currentDeciSecond * 10
                binding.tvCountdown.text =
                    String.format("%02d", countdownSecond) // 05초, 08초 식으로 표현, "%03d"면 011초
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