package com.nemocompany.chapter3.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ButtonExample(modifier: Modifier = Modifier, onButtonClicked: () -> Unit) {
    Button(onClick = onButtonClicked, modifier = modifier) {
        Text(text = "Send")
        // Vector 이미지를 버튼에 추가
        // contentDescription -> 보통 시각장애인 떄문에 생김
        Icon(imageVector = Icons.Filled.Send, contentDescription = null)
    }
}