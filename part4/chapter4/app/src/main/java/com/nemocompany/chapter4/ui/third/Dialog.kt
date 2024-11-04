package com.nemocompany.chapter4.ui.third

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Composable
fun DialogExample() {
    var openDialog by remember {
        mutableStateOf(false)
    }
    var counter by remember {
        mutableStateOf(0)
    }

    Column {
        Button(onClick = { openDialog = !openDialog }) {
            Text(text = "다이얼로그 열기")
        }
        Text(text = "카운터 $counter")
    }

    if (openDialog) {
        AlertDialog(
            // dialog의 밖을 눌렀을때
            onDismissRequest = { openDialog = false },
            // 확인 버튼
            confirmButton = {
                Button(onClick = {
                    counter++
                    openDialog = false
                }) {
                    Text(text = "더하기")
                }
            },
            // 취소버튼
            dismissButton = {
                Button(onClick = { openDialog = false }) {
                    Text(text = "취소")
                }
            },
            //dialog 제목
            title = {
                Text(text = "더하기")
            },
            // dialog 설명
            text = {
                Text(text = "더하기 버튼을 누르면 카운터를 증가합니다.")
            },
        )
    }
}