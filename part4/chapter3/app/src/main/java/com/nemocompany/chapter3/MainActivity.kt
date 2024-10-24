package com.nemocompany.chapter3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nemocompany.chapter3.ui.ButtonExample
import com.nemocompany.chapter3.ui.TextExample
import com.nemocompany.chapter3.ui.theme.Chapter3Theme

// 실제 적용되는 부분
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter3Theme {
                // Scaffold, modifier = 화면을 구성하는 큰 들 ( 부모 레이아웃 설정 )
                // 현재는 화면을 꽉 채울거냐, 에 따른 설정
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextExample(
                        modifier = Modifier.padding(innerPadding),
                        name = "Android",
                    )
                    ButtonExample(
                        modifier = Modifier.padding(innerPadding),
                        onButtonClicked =  {
                        Toast.makeText(this, "Send Clicked", Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter3Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            TextExample(
                name = "Android",
                modifier = Modifier.padding(innerPadding)
            )
            ButtonExample{

            }
        }
    }
}