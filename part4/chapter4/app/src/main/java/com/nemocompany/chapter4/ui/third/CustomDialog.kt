package com.nemocompany.chapter4.ui.third

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialogExample() {
    var openDialog by remember {
        mutableStateOf(false)
    }
    var counter by remember {
        mutableIntStateOf(0)
    }

    Column {
        Button(onClick = { openDialog = !openDialog }) {
            Text(text = "다이얼로그 열기")
        }
        Text(text = "카운터 $counter")
    }

    if (openDialog) {
        Dialog(onDismissRequest = {
            openDialog = false
        }) {
            // Surface를 써야 custom을 할 수 있음
            Surface {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "버튼을 클릭해주세요. \n +1을 누르면 값이 증가합니다. \n -1을 누르면 값이 감소합니다.")
                    Row(
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Button(onClick = {
                            openDialog = false
                        }) {
                            Text(text = "취소")
                        }
                        Button(onClick = {
                            openDialog = false
                            counter++
                        }) {
                            Text(text = "+1")
                        }
                        Button(onClick = {
                            openDialog = false
                            counter--
                        }) {
                            Text(text = "-1")
                        }
                    }
                }
            }
        }
    }
}