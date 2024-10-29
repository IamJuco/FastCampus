package com.nemocompany.chapter3.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SurfaceExample() {
    Surface(
        modifier = Modifier.padding(5.dp),
        // elevation 설정
        shadowElevation = 20.dp,
        border = BorderStroke(width = 2.dp, color = Color.Magenta),
        // 둥글게 만들기
        shape = CircleShape,
        // 배경색상 설정
        color = MaterialTheme.colorScheme.error
    ) {
        Text(text = "Surface", modifier = Modifier.padding(8.dp))
    }
}