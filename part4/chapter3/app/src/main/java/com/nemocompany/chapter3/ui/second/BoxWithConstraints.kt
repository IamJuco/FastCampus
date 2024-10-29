package com.nemocompany.chapter3.ui.second

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// 자주 사용하진 않지만, 높이 또는 가로가 몇 이상일때 요런거에대한 이벤트 처리 가능
@Composable
fun BoxWithConstraintsExample() {
    Column(modifier = Modifier.width(200.dp)) {
        Inner(
            modifier = Modifier
                .width(200.dp)
                .height(160.dp)
        )
        Inner(
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
        )
    }
}

@Composable
private fun Inner(modifier: Modifier = Modifier) {
    // BoxWithConstraints는 Width나 Height를 써야만 사용가능함
    // ex this.maxWidth도 가능
    BoxWithConstraints(modifier) {
        // maxHeight 값이 150dp가 넘을때만 추가 텍스트 출력하기
        if (maxHeight > 150.dp) {
            Text(text = "여기 꽤 길군요!", modifier = Modifier.align(Alignment.BottomCenter))
        }
        Text(text = "maxW: $maxWidth, maxH: $maxHeight, minW: $minWidth, minH: $minHeight")
    }
}