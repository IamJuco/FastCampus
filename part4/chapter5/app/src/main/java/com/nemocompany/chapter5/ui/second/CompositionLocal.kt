package com.nemocompany.chapter5.ui.second

import android.content.ContentProvider
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CompositionLocalExample() {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        // 이 안에있는 Text들 명암을 변경, 색상 변경도 가능, Elevation도 가능
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(
                alpha = 0.38f
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text("안녕하세요. 패스트캠퍼스")
                Text("스안녕하세요. 패스트캠퍼")
                Text("퍼스안녕하세요. 패스트캠")
                CompositionLocalProvider(
                    LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.86f
                    )
                ) {
                    Text("캠퍼스안녕하세요. 패스트")
                    Text("트캠퍼스안녕하세요. 패스")
                }
                Text("스트캠퍼스안녕하세요. 패")
                Text("패스트캠퍼스안녕하세요.")
            }
        }
    }
}