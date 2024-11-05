package com.nemocompany.chapter4.ui.fifth

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Animation2Example() {
    var isDarkMode by remember { mutableStateOf(false) }

    val transition = updateTransition(targetState = isDarkMode, label = "디크 모드 트랜지션")

    val backgroundColor by transition.animateColor(label = "다크 모드 애니메이션") { state ->
        when (state) {
            // 배경화면이 라이트모드
            false -> Color.White
            // 배경화면이 다크모드
            true -> Color.Black
        }
    }

    val textColor by transition.animateColor(label = "다크 모드 글자색상 애니메이션") { state ->
        when (state) {
            // 배경화면이 라이트모드 일떄 텍스트 색상
            false -> Color.Black
            // 배경화면이 다크모드 일떄 텍스트 색상
            true -> Color.White
        }
    }

    val alpha by transition.animateFloat(label = "다크 모드 알파 애니메이션") { state ->
        when (state) {
            false -> 0.7f
            true -> 1f
        }
    }

    Column(
        modifier = Modifier
            .background(backgroundColor)
            .alpha(alpha)
            .fillMaxSize()
    ) {
        RadioButtonWithText(text = "일반 모드", selected = !isDarkMode, color = textColor) {
            isDarkMode = false
        }
        RadioButtonWithText(text = "다크 모드", selected = isDarkMode, color = textColor) {
            isDarkMode = true
        }

        // Crossfade
        // 화면을 교체할때 부드럽게 보여지는 애니메이션
        Crossfade(targetState = isDarkMode, label = "") { state ->
            when (state) {
                false -> {
                    Column {
                        Box(
                            modifier = Modifier
                                .background(Color.Red)
                                .size(20.dp)
                        ) {
                            Text("1")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Magenta)
                                .size(20.dp)
                        ) {
                            Text("2")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Blue)
                                .size(20.dp)
                        ) {
                            Text("3")
                        }
                    }
                }

                true -> {
                    Row {
                        Box(
                            modifier = Modifier
                                .background(Color.Red)
                                .size(20.dp)
                        ) {
                            Text("A")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Magenta)
                                .size(20.dp)
                        ) {
                            Text("B")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Blue)
                                .size(20.dp)
                        ) {
                            Text("C")
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun RadioButtonWithText(
    text: String,
    color: Color = Color.Black,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.selectable(
            selected = selected,
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(text = text, color = color)
    }
}