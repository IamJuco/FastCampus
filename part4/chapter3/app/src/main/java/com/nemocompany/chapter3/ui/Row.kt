package com.nemocompany.chapter3.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// 가로 배치
@Composable
fun RowExample() {
    Row(
        modifier = Modifier.height(40.dp)
    ) {
        Text(text = "첫 번째", modifier = Modifier.align(Alignment.Top))
        // Row는 Center 사용 불가능, CenterVertically는 가능
        Text(text = "두 번째", modifier = Modifier.align(Alignment.CenterVertically))
        Text(text = "세 번째", modifier = Modifier.align(Alignment.Bottom))
    }
}

@Composable
fun RowExample2() {
    Row(
        modifier = Modifier.height(40.dp),
        // 만약 Text들을 전부 가운데에 두고싶다면 이렇게 한번에 처리가능
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 이렇게 하나만 modifier 설정 가능
        Text(text = "첫 번째", modifier = Modifier.align(Alignment.Top))
        Text(text = "두 번째")
        Text(text = "세 번째")
    }
}

@Composable
fun RowExample3() {
    Row(
        modifier = Modifier
            .height(40.dp)
            .width(200.dp),
        // 만약 Text들을 전부 가운데에 두고싶다면 이렇게 한번에 처리가능
        verticalAlignment = Alignment.CenterVertically,
        // horizontalArrangement = 가로로 정렬
        // SpaceBetween = 텍스트들 사이의 간격만 띄움 (양 옆 간격은 없음 )
        // SpaceEvenly = 동일한 간격으로 가로로 정렬해줌
        // Center = 텍스트들이 한곳에 모임 ( 가운데로 )
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // 이렇게 하나만 modifier 설정 가능
        Text(text = "첫 번째")
        Text(text = "두 번째")
        Text(text = "세 번째")
    }
}

@Composable
fun RowExample4() {
    Row(
        modifier = Modifier
            .height(40.dp)
            .width(200.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // 각 텍스트들 weight로 간격 처리 가능
        Text(text = "첫 번째", Modifier.weight(1f).background(Color.Red), textAlign = TextAlign.End)
        Icon(imageVector = Icons.Filled.Add, contentDescription = "추가", modifier = Modifier.background(Color.Yellow))
        Text(text = "세 번째", Modifier.weight(1f).background(Color.Blue))
    }
}