package com.nemocompany.chapter4.ui.third

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun BottomAppBarExample() {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()
    var counter by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            BottomAppBar {
                Text(text = "헬로")
                Button(onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("안녕하세요")
                    }
                }) {
                    Text(text = "인사하기")
                }
                Button(onClick = {
                    counter++
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("${counter}입니다.")
                    }
                }) {
                    Text(text = "더하기")
                }
                Button(onClick = {
                    counter--
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("${counter}입니다.")
                    }
                }) {
                    Text(text = "빼기")
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "카운터는 ${counter}회입니다.", modifier = Modifier.align(Alignment.Center))
        }
    }
}