package com.nemocompany.chapter4.ui.fourth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// State
@Composable
fun PyeongToSquareMeter() {
    // rememberSaveable == 상태유지를 시켜줌 ( ViewModel과 같음 )
    // 많이 사용은 안하는듯, ViewModel이 있으니까
    var pyeong by rememberSaveable {
        mutableStateOf("123")
    }
    var squaremeter by rememberSaveable {
        mutableStateOf((23 * 3.306).toString())
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = {
                if (it.isEmpty()) {
                    pyeong = ""
                    squaremeter = ""
                    return@OutlinedTextField
                }
                // Float이면 null로 반환
                val numericValue = it.toFloatOrNull() ?: return@OutlinedTextField
                pyeong = it
                squaremeter = (numericValue * 3.306).toString()
            },
            label = {
                Text(text = "평")
            })

        OutlinedTextField(
            value = squaremeter,
            onValueChange = {},
            label = {
                Text(text = "제곱미터")
            })
    }

    PyeongToSquareMeterStateless(pyeong, squaremeter) {
        if (it.isEmpty()) {
            pyeong = ""
            squaremeter = ""
            return@PyeongToSquareMeterStateless
        }
        // Float이면 null로 반환
        val numericValue = it.toFloatOrNull() ?: return@PyeongToSquareMeterStateless
        pyeong = it
        squaremeter = (numericValue * 3.306).toString()
    }
}

// StateHoisting
// 상태가 없는 함수, 재사용이 용이함
@Composable
fun PyeongToSquareMeterStateless(
    pyeong: String,
    squareMeter: String,
    onPyeongChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            // onValueChange는 상태에 대해서 모르고 일단 변경해줌
            onValueChange = onPyeongChange,
            label = {
                Text(text = "평")
            })

        OutlinedTextField(
            value = squareMeter,
            onValueChange = {},
            label = {
                Text(text = "제곱미터")
            })
    }
}