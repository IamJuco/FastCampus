package com.nemocompany.chapter4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.nemocompany.chapter4.ui.fifth.Animation1Example
import com.nemocompany.chapter4.ui.fifth.Animation2Example
import com.nemocompany.chapter4.ui.fifth.RadioButtonWithText
import com.nemocompany.chapter4.ui.theme.Chapter4Theme

class FifthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Animation1Example()
                        Animation2Example()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GreetingPreview5() {
    Chapter4Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                RadioButtonWithText(
                    text = "라디오 버튼",
                    color = Color.Red,
                    selected = true,
                    onClick = {}
                )
                Animation2Example()
            }
        }
    }
}