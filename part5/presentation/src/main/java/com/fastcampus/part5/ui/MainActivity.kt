package com.fastcampus.part5.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.fastcampus.part5.ui.theme.ShoppingMallTheme
import com.fastcampus.part5.viewmodel.MainViewModel
import com.fastcampus.part5.viewmodel.TempViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    private val viewModel: TempViewModel by viewModels()
private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingMallTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainScreen()
                }
            }
        }
        viewModel.updateColumnCount(getColumnCount())
    }

    private fun getColumnCount(): Int {
        return getDisplayWidthDp().toInt() / DEFAULT_COLUMN_SIZE
    }

    // display의 dp값을 가져옴
    private fun getDisplayWidthDp(): Float {
        return resources.displayMetrics.run {
            widthPixels / density
        }
    }
    companion object {
        private const val DEFAULT_COLUMN_SIZE = 120
    }
}