package com.nemocompany.movieappworkspace.ui.components.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoryRow() {
    Column {
        CategoryTitle("Action")
        LazyRow(
            // 아이템들 간의 간격 padding 조정
            contentPadding = PaddingValues(
                horizontal = 10.dp
            )
        ) {
            item {
                MovieItem()
                MovieItem()
                MovieItem()
                MovieItem()
                MovieItem()
            }
        }
    }
}

@Composable
fun CategoryTitle(s: String) {
    Text(
        text = "Action",
        modifier = Modifier.padding(10.dp)
    )
}

@Preview
@Composable
fun CategoryRowPreview() {
    CategoryRow()
}