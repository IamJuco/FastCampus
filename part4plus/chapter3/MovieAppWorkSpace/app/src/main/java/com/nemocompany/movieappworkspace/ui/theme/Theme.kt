package com.nemocompany.movieappworkspace.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.nemocompany.movieappworkspace.ui.config.ComponentConfig
import com.nemocompany.movieappworkspace.ui.config.DefaultComponentConfig
import com.nemocompany.movieappworkspace.ui.theme.color.ColorSet
import com.nemocompany.movieappworkspace.ui.theme.color.MyColors

private val LocalColors = staticCompositionLocalOf { ColorSet.Red.lightColors }

@Composable
fun MovieAppWorkSpaceTheme(
//    myColors: ColorSet = ColorSet.Red,
//    typography: Typography = Typography,
//    shapes: Shapes = Shapes,
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
    themeState: State<ComponentConfig> = mutableStateOf(
        DefaultComponentConfig.RED_THEME
    ),
    content: @Composable () -> Unit
) {
    val myTheme by remember {
        themeState
    }

    val currentColors = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            // dynamicColor를 지원하지 않는 강의여서 삭제
////            val context = LocalContext.current
//            if (myTheme.isDarkTheme) myTheme.colors.darkColors else myTheme.colors.lightColors
//        }

        myTheme.isDarkTheme -> myTheme.colors.darkColors
        else -> myTheme.colors.lightColors
    }

    CompositionLocalProvider(LocalColors provides currentColors) {
        MaterialTheme(
            colorScheme = currentColors.colorScheme,
            typography = myTheme.typography,
            content = content,
            shapes = myTheme.shapes
        )
    }
}

// 강의에선 써야하지만 이미 Material3 에서 colorScheme을 쓰고 있기에 안쓰고 colorScheme으로 대체
val MaterialTheme.colorSchemes: MyColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current