package com.nemocompany.chapter4.ui.fifth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
fun Animation1Example() {
    var helloWorldVisible by remember { mutableStateOf(true) }
    var isRed by remember { mutableStateOf(false) }

    // by를 안붙이고 = 을 쓰게되면 .value값을 써야함
    // ex) backgroundColor.value
    val backgroundColor by animateColorAsState(
        targetValue = if (isRed) Color.Red else Color.White,
        label = ""
    )

    // 색상의 알파값을 바꿀 수 있음 ( 좀 더 흐리거나 진하거나, 그냥 알고 넘어갈것 )
    val alpha by animateFloatAsState(targetValue = if (isRed) 1.0f else 0.5f, label = "")

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(backgroundColor)
            // 이건 설정 안해도됌, alpha
            .alpha(alpha)
    ) {
        // visibility 설정
        // Compose에는 View.Gone 같은게 없다.
        AnimatedVisibility(
            visible = helloWorldVisible,
            // enter = 컴포넌트가 화면에 나타날때 애니메이션 효과
            // + 로 두가지의 애니메이션 효과 줄수 있음
            enter = slideInVertically() + expandHorizontally(),
            // exit = 컴포넌트가 화면에서 사라질때 애니메이션 효과
            exit = shrinkVertically()
        )
        {
            Text(text = "Hello World!", modifier = Modifier.padding(8.dp))
        }

        Row(
            Modifier.selectable(
                selected = helloWorldVisible,
                onClick = {
                    helloWorldVisible = true
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = helloWorldVisible,
                onClick = { helloWorldVisible = true }
            )
            Text(
                text = "Hello World 보이기"
            )
        }

        Row(
            Modifier.selectable(
                selected = !helloWorldVisible,
                onClick = {
                    helloWorldVisible = false
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !helloWorldVisible,
                onClick = { helloWorldVisible = false }
            )
            Text(
                text = "Hello World 감추기"
            )
        }

        Text(text = "배경 색을 바꾸어봅시다.")

        Row(
            Modifier.selectable(
                selected = !isRed,
                onClick = {
                    isRed = false
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !isRed,
                onClick = { isRed = false }
            )
            Text(
                text = "흰색"
            )
        }

        Row(
            Modifier.selectable(
                selected = isRed,
                onClick = {
                    isRed = true
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isRed,
                onClick = { isRed = true }
            )
            Text(
                text = "빨간색"
            )
        }
    }
}