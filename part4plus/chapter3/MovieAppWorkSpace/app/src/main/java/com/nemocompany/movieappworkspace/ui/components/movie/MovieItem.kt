package com.nemocompany.movieappworkspace.ui.components.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nemocompany.movieappworkspace.R
import com.nemocompany.movieappworkspace.ui.theme.Paddings

private val CARD_WIDTH = 150.dp
private val ICON_SIZE = 12.dp

@Composable
fun MovieItem() {
    Column(
        modifier = Modifier
            .width(CARD_WIDTH)
            .padding(Paddings.large)
    ) {
        Poster(
            modifier = Modifier.width(CARD_WIDTH)
        )

        Text(
            text = "The Lord The Ring 1",
            maxLines = 1,
            // 텍스트가 주어진 공간보다 더 길경우 어떻게 보여질지
            // 화면 크기또는 텍스트 크기에따른 예외처리
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(
                top = Paddings.large
            ),
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            modifier = Modifier.padding(vertical = Paddings.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(Paddings.small)
                    .size(ICON_SIZE),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_rating),
                tint = Color.Black.copy(
                    alpha = 0.5f
                ),
                contentDescription = "rating icon"
            )
            Text(
                text = "5.0",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.5f
                )
            )
        }
    }
}

@Composable
fun Poster(
    modifier: Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        )
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem()
}