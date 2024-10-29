package com.nemocompany.chapter3.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ModifierExample1() {
// height와 width를 각각 설정할 수 있음
    Button(
        onClick = {}, modifier = Modifier
            .height(100.dp)
            .width(200.dp)
    ) {
        Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        // 아이콘과 텍스트 사이의 거리 조절
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Search")
    }
}


@Composable
fun ModifierExample2() {
    // size로 height와 width 설정 가능
    Button(
        onClick = {},
        modifier = Modifier
            .size(200.dp, 100.dp)
            .padding(10.dp),
        // 버튼 background color와 text color 설정
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Blue,
            containerColor = Color.Green
        ),
        enabled = false
    ) {
        Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        // 버튼 자체는 enable = false 해놓고 text를 클릭했을때 클릭이벤트 주기
        Text(text = "Search",
            modifier = Modifier.clickable { })
    }
}

@Composable
fun ModifierExample3() {
    Button(
        onClick = {},
        modifier = Modifier
            .size(200.dp, 100.dp)
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Blue,
            containerColor = Color.Green
        ),
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            // Icon의 background 색상 지정
            Modifier.background(Color.Black)
        )
        Spacer(
            modifier = Modifier
                .size(ButtonDefaults.IconSpacing)
                // Icon과 Text사이의 거리 사이에 background 색상 지정
                .background(Color.Red)
        )
        Text(
            text = "Search",
            // text의 위치 조정 가능
            Modifier
                .offset(x = 10.dp)
                .background(Color.Black)
        )
    }
}