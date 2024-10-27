package com.nemocompany.chapter3.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextExample(name: String) {
    Text(
        text = "안녕하세요 $name",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold, // textStyle
        fontFamily = FontFamily.Monospace, // 폰트
        textDecoration = TextDecoration.Underline, // Text 밑줄및 취소선
        textAlign = TextAlign.Start, // TextGravity
        modifier = Modifier.padding(30.dp), // TextPadding
//        modifier = modifier.width(200.dp), // text의 가로 길이, height와 size도 있음 size = 가로,높이
        letterSpacing = 2.sp // text끼리의 간격

    )
}