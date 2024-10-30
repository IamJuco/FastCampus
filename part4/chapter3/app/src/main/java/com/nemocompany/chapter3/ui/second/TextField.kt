package com.nemocompany.chapter3.ui.second

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// EditText
@Composable
fun TextFieldExample() {
    var name by remember {
        mutableStateOf("Tom")
    }
    Column(modifier = Modifier.padding(16.dp)) {
        // OutlineTextField 도 있음 ( 이게더 깔끔함 )
        TextField(
            value = "Tom",
            onValueChange = { name = it },
            // TextField의 제목
            label = { Text(text = "이름") }
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Hello $name")
    }
}