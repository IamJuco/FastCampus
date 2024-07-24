package com.example.chapter4

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.chapter4.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        uiSetting()

        // Spinner 사용 방법
        // values에 arrays.xml을 만들어서 string-array로 처리하는법
        binding.spBloodType.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )

        binding.birthDateLayer.setOnClickListener {
            val listener = OnDateSetListener { _, year, month, dayOfMonth ->
                binding.tvBirthDateValue.text = "$year-${month.inc()}-$dayOfMonth"
            }
            DatePickerDialog(this,
                listener,
                2000,
                1,
                1
                ).show()
        }

        // isChecked의 여부에 따라 checkbox를 클릭했을때 view가 보일지 말지 선택
        binding.cbWarning.setOnCheckedChangeListener { _, isChecked ->
            binding.etWarningValue.isVisible = isChecked
        }
        binding.etWarningValue.isVisible = binding.cbWarning.isChecked // 처음 체크박스가 눌려있지 않을때 View가 보이지 않도록 처리

        binding.fabSave.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun uiSetting() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun saveData() {
        with(getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).edit()) {
            putString(NAME, binding.etNameValue.text.toString())
            putString(BLOOD_TYPE, getBloodType())
            putString(EMERGENCY_CONTACT, binding.etEmergencyContactValue.text.toString())
            putString(BIRTHDATE, binding.tvBirthDateValue.text.toString())
            putString(WARNING, getWarning())
            apply()
        }

        Toast.makeText(this,"저장을 완료 했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getBloodType() : String {
        val bloodAlphabet = binding.spBloodType.selectedItem.toString()
        val bloodSign = if (binding.rbBloodTypePlus.isChecked) "+" else "-"
        return "$bloodSign$bloodAlphabet"
    }

    private fun getWarning() : String {
        return if (binding.cbWarning.isChecked) binding.etWarningValue.text.toString() else ""
    }
}