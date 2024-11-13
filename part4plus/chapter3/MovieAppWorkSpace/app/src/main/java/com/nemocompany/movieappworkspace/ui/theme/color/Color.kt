package com.nemocompany.movieappworkspace.ui.theme.color

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// 기존에 있던 Color 값
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// 강의에 의해 추가한 Color 값
val Red200 = Color(0xFFFFAAAA)
val Red300 = Color(0xFFCC5942)
val Red400 = Color(0xFFFF5258)
val Red700 = Color(0xFFEC0000)
val Red800 = Color(0xFFAF0000)
val Red900 = Color(0xFF531F1C)
val Purple200 = Color(0xFF908499)
val Purple400 = Color(0xFF6D59FF)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF635270)
val Purple900 = Color(0xFF200833)
val Green400 = Color(0xFF55D800)
val Blue400 = Color(0xFF395DE8)
val Grey200 = Color(0xFF908499)
val Grey900 = Color(0xFF151515)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

// Color를 정의하고 상속 받아 사용하기 위해 Sealed class로 정의
// 강의에선 open lateinit var를 사용했지만
// sealed class의 open lateinit은 object의 특성과 충돌하며, lateinit 변수를 오버라이드하는 것이 불가능
// 따라서 abstract val를 사용하여 오버라이드 했음
sealed class ColorSet {
    abstract val lightColors: MyColors
    abstract val darkColors: MyColors

    data object Red : ColorSet() {
        override val lightColors = MyColors(
            colorScheme = lightColorScheme(
                primary = Red700,
                primaryContainer = Red800,
                secondary = Purple900,
                secondaryContainer = Purple700,
                surface = White,
                onSurface = Black,
                background = White,
                onBackground = Black,
                error = Red400,
                onPrimary = White
            ),
            success = Green400,
            disabledSecondary = Grey200,
            textFiledBackground = Grey200
        )

        override val darkColors = MyColors(
            colorScheme = darkColorScheme(
                primary = Purple900,
                primaryContainer = Red800,
                secondary = Purple900,
                secondaryContainer = Purple700,
                surface = Grey900,
                onSurface = White,
                background = Grey900,
                onBackground = White,
                error = Red400,
                onPrimary = White
            )
        )
    }

    object Blue : ColorSet() {
        override val lightColors = MyColors(
            colorScheme = lightColorScheme(
                primary = Blue400,
                primaryContainer = Purple400,
                secondary = Red900,
                secondaryContainer = Red700,
                surface = White,
                onSurface = Purple900,
                background = White,
                onBackground = Purple900,
                error = Red400,
                onPrimary = White
            ),
            success = Green400,
            disabledSecondary = Purple700,
            textFiledBackground = Grey200
        )

        override val darkColors = MyColors(
            colorScheme = darkColorScheme(
                primary = Red900,
                primaryContainer = Purple400,
                secondary = Blue400,
                secondaryContainer = Purple900,
                surface = Grey900,
                onSurface = White,
                background = Grey900,
                onBackground = White,
                error = Red400,
                onPrimary = White
            )
        )
    }
}