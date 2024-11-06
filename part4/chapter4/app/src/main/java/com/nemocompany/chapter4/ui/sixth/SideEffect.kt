package com.nemocompany.chapter4.ui.sixth

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun EffectExample(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {
    val snackbarHostState = SnackbarHostState()

    // `LaunchedEffect`는 `CouroutineScope`를 만들기 때문에 스코프를 별도로 설정할 필요없음
    LaunchedEffect(key1 = snackbarHostState) {
        snackbarHostState.showSnackbar("헬로 컴포즈")
    }

    // 단계 2: `DisposableEffect`를 호출하고 파리미터로 `lifecycleOwner`를
    // 전달합니다.

    // `LifecycleEventObserver`를 상속받고 두 상태에 대해 로그를 남깁니다.
    // `Lifecycle.Event.ON_START`, `Lifecycle.Event.ON_STOP`

    // 블록 내에서 `lifecycleOwner.lifecycle.addObserver`로 옵저버를 추가하고
    // onDispose에서 옵저버를 제거합니다.
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    Log.d("이펙트", "ON_START")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.d("이펙트", "ON_STOP")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("이펙트", "ON_PAUSE")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.d("이펙트", "ON_RESUME")
                }
                else -> {
                    Log.d("이펙트", "그외!")
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer = observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer = observer)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
    }
}