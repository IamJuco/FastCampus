package com.nemocompany.chapter4.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintLayoutExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (redBox, magentaBox, greenBox, yellowBox) = createRefs()
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                bottom.linkTo(parent.bottom, margin = 8.dp)
                end.linkTo(parent.end, margin = 4.dp)
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Magenta)
            .constrainAs(magentaBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Green)
            .constrainAs(greenBox) {
                // 각 link에 start end top bottom 할 필요없이
                // centerTo 이용하면 센터에 ui를 적용 가능
                // horizontal, vertically도 있음
                centerTo(parent)
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                start.linkTo(magentaBox.end)
                top.linkTo(magentaBox.bottom)
            })
    }
}