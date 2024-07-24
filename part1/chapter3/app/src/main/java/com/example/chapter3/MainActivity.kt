package com.example.chapter3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.chapter3.databinding.ActivityMainBinding
/**
 * 화면 회전시 onSaveInstanceState를 이용하여 데이터 저장
 * onRestoreInstanceState로 UI 복원시 데이터 가져옴 ( onStart에 실행됨 )
 * */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var inputNumber: Int = 0
    private var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputUnitEditText = binding.etNaturalNumber
        val outPutUnitNumber = binding.tvOutPutUnitNumber
        val cmTextView = binding.tvCm
        val mTextView = binding.tvM
        val swapImageButton = binding.btnNumberConvert

        // EditText에 입력시 연동한 Text도 같이 값이 변경됨
        inputUnitEditText.addTextChangedListener {
            inputNumber = if (it.isNullOrEmpty()) {
                0
            } else {
                it.toString().toInt()
            }

            if (cmToM) {
                outPutUnitNumber.text = inputNumber.times(0.01).toString()
            } else {
                outPutUnitNumber.text = inputNumber.times(100).toString()
            }

            swapImageButton.setOnClickListener {
                cmToM = !cmToM
                if (cmToM) {
                    cmTextView.text = "cm"
                    mTextView.text = "m"
                    outPutUnitNumber.text = inputNumber.times(0.01).toString()
                } else {
                    cmTextView.text = "m"
                    mTextView.text = "cm"
                    outPutUnitNumber.text = inputNumber.times(100).toString()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        Log.d("cmToM", cmToM.toString())
        binding.tvCm.text = if (cmToM) "cm" else "m"
        binding.tvM.text = if (cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}