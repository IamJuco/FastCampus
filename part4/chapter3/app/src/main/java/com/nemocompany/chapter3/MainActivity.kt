package com.nemocompany.chapter3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nemocompany.chapter3.ui.theme.Chapter3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter3Theme {

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        modifier = Modifier.size(300.dp),
        text = "Hello $name\nHello $name\nHello $name",
        color = Color(0xffff9944),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold, // textStyle
        fontFamily = FontFamily.Monospace, // font
        letterSpacing = 2.sp, // 단어 하나하나 마다 간격을 벌려줌
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.Center, // textGravity
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter3Theme {
        Greeting("Android")
    }
}