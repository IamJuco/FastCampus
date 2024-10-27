package com.nemocompany.chapter3.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// layout Box
@Composable
fun BoxExample1() {
    Box(
        modifier = Modifier.size(100.dp)
    ) {
        Text(text = "Hello World", modifier = Modifier.align(Alignment.TopStart))
        Text(text = "Jetpack", modifier = Modifier.align(Alignment.CenterEnd))
    }
}

@Composable
fun BoxExample2() {
    // box안에 box 설정 가능
    Box(
        modifier = Modifier.size(100.dp)
    ) {
        Box(modifier = Modifier
            .size(70.dp)
            .background(Color.Cyan)
            .align(Alignment.CenterStart))
        Box(modifier = Modifier
            .size(70.dp)
            .background(Color.Yellow)
            .align(Alignment.BottomEnd))
    }
}

// Box의 fillMaxSize
//@Composable
//fun BoxExample3() {
//    Box {
//        Box(modifier = Modifier.fillMaxSize().background(Color.Cyan))
//        Box(modifier = Modifier.size(70.dp).background(Color.Yellow).align(Alignment.Center))
//    }
//}