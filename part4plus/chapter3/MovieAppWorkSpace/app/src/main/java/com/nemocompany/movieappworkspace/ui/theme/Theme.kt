package com.nemocompany.movieappworkspace.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.nemocompany.movieappworkspace.ui.theme.color.ColorSet

private val LocalColors = staticCompositionLocalOf { ColorSet.Red.lightColors }

@Composable
fun MovieAppWorkSpaceTheme(
    myColors: ColorSet,
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val currentColors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // dynamicColor를 지원하지 않는 강의여서 삭제
//            val context = LocalContext.current
            if (darkTheme) myColors.darkColors else myColors.lightColors
        }

        darkTheme -> myColors.darkColors
        else -> myColors.lightColors
    }

    CompositionLocalProvider(LocalColors provides currentColors) {
        MaterialTheme(
            colorScheme = currentColors.colorScheme,
            typography = Typography,
            content = content
        )
    }
}