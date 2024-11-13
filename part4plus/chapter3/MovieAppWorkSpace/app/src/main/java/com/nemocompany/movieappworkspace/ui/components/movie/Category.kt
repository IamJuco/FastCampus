package com.nemocompany.movieappworkspace.ui.components.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nemocompany.movieappworkspace.ui.theme.MovieAppWorkSpaceTheme
import com.nemocompany.movieappworkspace.ui.theme.Paddings

@Composable
fun CategoryRow() {
    Column {
        CategoryTitle("Action")
        LazyRow(
            // 아이템들 간의 간격 padding 조정
            contentPadding = PaddingValues(
                horizontal = Paddings.large
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
        modifier = Modifier.padding(
            vertical = Paddings.large,
            horizontal = Paddings.extra
        ),
        style = MaterialTheme.typography.headlineMedium
    )
}

@Preview
@Composable
fun CategoryRowPreview() {
    MovieAppWorkSpaceTheme {
        CategoryRow()
    }
}