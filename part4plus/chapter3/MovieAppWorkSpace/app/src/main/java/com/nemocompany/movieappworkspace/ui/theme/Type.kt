package com.nemocompany.movieappworkspace.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.nemocompany.movieappworkspace.R

private val spoqaBold = FontFamily(
    Font(R.font.spoqa_han_sans_neo_bold, FontWeight.Bold)
)
private val spoqaRegular = FontFamily(
    Font(R.font.spoqa_han_sans_neo_regular, FontWeight.Normal)
)
private val spoqaThin = FontFamily(
    Font(R.font.spoqa_han_sans_neo_thin, FontWeight.Thin)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = spoqaBold,
        fontWeight = FontWeight.Bold,
        fontSize = 60.sp
    ),
    displayMedium = TextStyle(
        fontFamily = spoqaBold,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    displaySmall = TextStyle(
        fontFamily = spoqaBold,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = spoqaThin,
        fontWeight = FontWeight.Thin,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = spoqaBold,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle(
        fontFamily = spoqaRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    titleMedium = TextStyle(
        fontFamily = spoqaRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    titleSmall = TextStyle(
        fontFamily = spoqaRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = spoqaBold,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = spoqaRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    bodySmall = TextStyle(
        fontFamily = spoqaRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = spoqaBold,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
)

val Typography.h5Title: TextStyle
    @Composable get() = headlineMedium.copy(
        fontSize = 24.sp
    )

val Typography.dialogButton: TextStyle
    @Composable get() = dialogButton.copy(
        fontSize = 18.sp
    )

val Typography.underlinedDialogButton: TextStyle
    @Composable get() = underlinedDialogButton.copy(
        textDecoration = TextDecoration.Underline
    )

val Typography.underlinedButton: TextStyle
    @Composable get() = underlinedButton.copy(
        textDecoration = TextDecoration.Underline
    )