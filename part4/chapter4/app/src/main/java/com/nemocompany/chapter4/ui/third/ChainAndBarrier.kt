package com.nemocompany.chapter4.ui.third

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ChainAndBarrierExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (redBox, magentaBox, yellowBox, text) = createRefs()
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Magenta)
            .constrainAs(magentaBox) {
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
            })
        // 세로로 체인
//        createVerticalChain(redBox, magentaBox, yellowBox)
        // 가로로 체인
        // chainStyle로 체인을 어떤식으로 할지 정할 수 있음,
        // Packed = 컴포넌트끼리 붙여줌, SpreadInside = 컴포넌트 간 사이값을 늘림 ( 컴포넌트는 양옆에 붙임 )
        createHorizontalChain(redBox, magentaBox, yellowBox, chainStyle = ChainStyle.Packed)
    }
}

@Composable
fun ChainAndBarrierExample2() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (redBox, magentaBox, yellowBox, text) = createRefs()
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                top.linkTo(parent.top, margin = 18.dp)
            })

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Magenta)
            .constrainAs(magentaBox) {
                top.linkTo(parent.top, margin = 32.dp)
            })

        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                top.linkTo(parent.top, margin = 20.dp)
            })
        createHorizontalChain(redBox, magentaBox, yellowBox, chainStyle = ChainStyle.SpreadInside)

        // barrier, top start, end 다 있음
        val barrier = createBottomBarrier(redBox, magentaBox, yellowBox)
        Text(text = "나랏말쌈이 뒹귈에달아", modifier = Modifier.constrainAs(text) {
            // top = barrier의 컴포넌트중 가장 아래에 있는 곳에 Text 설정
            // bottom = barrier의 컴포넌트중 가장 위에 있는 곳에 Text 설정
            top.linkTo(barrier)
        })
    }
}