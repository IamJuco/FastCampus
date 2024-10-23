package com.nemocompany.chapter3.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ButtonExample(modifier: Modifier = Modifier, onButtonClicked: () -> Unit) {
    Button(onClick = onButtonClicked, modifier = modifier) {
        Text(text = "Send")
    }
}