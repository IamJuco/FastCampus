package com.nemocompany.chapter3.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColumnExample() {
    // 세로 배치
    // 칼럼에도 weight가 있다.
    Column(
        // 가로로 가운대 배치
        horizontalAlignment = Alignment.CenterHorizontally,
        // 세로로 탑에 배치
        verticalArrangement = Arrangement.Top,
        // 가로로 배치는 CenterHorizontally를 써야하는데 세로로 배치는 Center만 써도됌 ( 좀 통합좀 시켜줘라! )
        //verticalArrangement = Arrangement.Center
        modifier = Modifier.size(100.dp)
    ) {
        Text(text = "첫 번째")
        Text(text = "두 번째")
        Text(text = "세 번째")
    }
}

@Composable
fun ColumnExample2() {
    // 세로 배치
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.size(100.dp)
    ) {
        Text(text = "첫 번째", modifier = Modifier.align(Alignment.Start))
        Text(text = "두 번째")
        Text(text = "세 번째", modifier = Modifier.align(Alignment.End))
    }
}