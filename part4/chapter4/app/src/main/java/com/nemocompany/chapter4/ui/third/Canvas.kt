package com.nemocompany.chapter4.ui.third

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Canvas는 Modifier가 기본값이 없어서 무조건 직접 선언해줘야함
@Composable
fun CanvasExample() {
    Canvas(modifier = Modifier.size(20.dp)) {
        drawLine(Color.Red, Offset(30f, 10f), Offset(50f, 40f))
        drawCircle(Color.Yellow, 30f, Offset(15f, 40f))
        // 사각형
        drawRect(Color.Magenta, Offset(30f, 30f), Size(10f, 10f))

        // SendIcons를 CanVas로 만들어 봤음
        drawLine(Color.Green, Offset(2.01f, 21.0f), Offset(23.0f, 12.0f))
        drawLine(Color.Green, Offset(23.0f, 12.0f), Offset(2.01f, 3.0f))
        drawLine(Color.Green, Offset(2.01f, 3.0f), Offset(2.01f, 10.0f))
        drawLine(Color.Green, Offset(2.0f, 10.0f), Offset(17.0f, 12.0f))
        drawLine(Color.Green, Offset(17.0f, 12.0f), Offset(2.01f, 14.0f))
        drawLine(Color.Green, Offset(2.0f, 14.0f), Offset(2.01f, 21.0f))

    }
}