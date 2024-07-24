package com.example.chapter4

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        uiSetting()

        binding.fabMoveToActivity.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("intentMessage", "응급의료정보")
            startActivity(intent)
        }

        binding.fabDelete.setOnClickListener {
            deleteDate()
        }

        // 전화 앱 연결
        binding.emergencyContactLayer.setOnClickListener {
            with(Intent(Intent.ACTION_VIEW)) {
                val phoneNumber = binding.tvEmergencyContactValue.text.toString()
                    .replace("-","") // 핸드폰 번호 입력 할 때 입력 값에 - 가 있으면 없애줌
                data = Uri.parse("tel:$phoneNumber")
                startActivity(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getDataAndUiUpdate()
    }

    private fun uiSetting() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getDataAndUiUpdate() {
        with(getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)) {
            binding.tvNameValue.text = getString(NAME, "미정")
            binding.tvBirthDateValue.text = getString(BIRTHDATE, "미정")
            binding.tvBloodTypeValue.text = getString(BLOOD_TYPE, "미정")
            binding.tvEmergencyContactValue.text = getString(EMERGENCY_CONTACT, "미정")

            val warning = getString(WARNING, "미정")
            binding.tvWarningValue.isVisible = warning.isNullOrEmpty().not()
            binding.tvWarning.isVisible = warning.isNullOrEmpty().not()

            if (!warning.isNullOrEmpty()) {
                binding.tvWarningValue.text = warning
            }
        }
    }

    private fun deleteDate() {
        with(getSharedPreferences(USER_INFO, MODE_PRIVATE).edit()) {
            clear()
            apply()
            getDataAndUiUpdate()
        }
        Toast.makeText(this,"초기화를 완료했습니다.", Toast.LENGTH_SHORT).show()
    }
}