package com.nemocompany.chapter3.ui.second

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.nemocompany.chapter3.R

@Composable
fun ImageExample() {
    Column {
        // painter 사용법 ( drawable 파일 가져오는 법 )
        Image(
            painter = painterResource(id = R.drawable.wall_mdpi),
            contentDescription = "엔텔로프 캐넌"
        )
        // imageVector 사용법 ( 이미 제공하는 Icon 들을 가져올 수 있음 )
        Image(
            imageVector = Icons.Filled.Settings,
            contentDescription = "세팅"
        )
//        // 비트맵도 가능
//        Image(bitmap = , contentDescription = )

    }
}