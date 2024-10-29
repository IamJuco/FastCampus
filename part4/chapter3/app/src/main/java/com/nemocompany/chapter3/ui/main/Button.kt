package com.nemocompany.chapter3.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonExample(modifier: Modifier = Modifier, onButtonClicked: () -> Unit) {
    Button(
        onClick = onButtonClicked,
        modifier = modifier,
        enabled = true,
        border = BorderStroke(10.dp, Color.Magenta), // 가장자리 색깔 설정
        shape = RoundedCornerShape(10.dp), // 가장자리  radius 설정
        contentPadding = PaddingValues(20.dp) // 버튼 패딩 설정
    ) {
        Text(text = "Send")
        // Vector 이미지를 버튼에 추가
        // contentDescription -> 보통 시각장애인 떄문에 생김
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null
        )
        Spacer(modifier = modifier.size(ButtonDefaults.IconSpacing)) // 버튼 안의 아이콘과 텍스트 사이 간격 설정, 또는 30.dp
    }
}