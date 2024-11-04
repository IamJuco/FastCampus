package com.nemocompany.chapter4.ui.third

import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

@Composable
fun SnackbarExample() {
    var counter by remember {
        mutableIntStateOf(0)
    }
    // suspend 함수를 사용하기위해 코루틴 스코프를 만듬, (lauch 때문에)
    val coroutineScope = rememberCoroutineScope()

    // rememberScaffoldState() 가 사라짐
    val snackbarHostState = SnackbarHostState()

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->
        Button(onClick = {
            counter++
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "카운터는 ${counter}입니다.",
                    actionLabel = "닫기",
                    duration = SnackbarDuration.Short
                )
            }
        }) {
            Text(text = "더하기")
        }
    }
}

@Composable
fun SnackbarExample2() {
    var counter by remember {
        mutableIntStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()

    val snackbarHostState = SnackbarHostState()
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->
        Button(onClick = {
            counter++
            coroutineScope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = "카운터는 ${counter}입니다.",
                    actionLabel = "닫기",
                    duration = SnackbarDuration.Short
                )
                // 이런식으로 Snackbar가 호출됐는지, 안됐는지에 따라 액션을 취할 수 있음
                when (result) {
                    SnackbarResult.Dismissed -> {}
                    SnackbarResult.ActionPerformed -> {}
                }
            }
        }) {
            Text(text = "더하기")
        }
    }
}

@Composable
fun SnackbarExample3() {
    var counter by remember {
        mutableIntStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()

    val snackbarHostState = SnackbarHostState()

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->

        // 만약 버튼등 액션이 없을떄 사용하고싶으면 이렇게 LaunchedEffect를 가져와야
        LaunchedEffect(snackbarHostState) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "카운터는 ${counter}입니다.",
                    actionLabel = "닫기",
                    duration = SnackbarDuration.Short
                )
            }
        }

        Button(onClick = {
            counter++
        }) {
            Text(text = "더하기")
        }
    }
}