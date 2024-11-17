package com.nemocompany.movieappworkspace.ui.components.movie

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import com.nemocompany.movieappworkspace.R
import com.nemocompany.movieappworkspace.features.common.entity.MovieFeedItemEntity
import com.nemocompany.movieappworkspace.features.feed.presentation.input.IFeedViewModelInput
import com.nemocompany.movieappworkspace.ui.theme.Paddings
import timber.log.Timber

private val CARD_WIDTH = 150.dp
private val ICON_SIZE = 12.dp

@Composable
fun MovieItem(
    movie: MovieFeedItemEntity,
    input: IFeedViewModelInput
) {
    Column(
        modifier = Modifier
            .width(CARD_WIDTH)
            .padding(Paddings.large)
    ) {
        Poster(
            input = input,
            thumbnailMovie = movie
        )

        Text(
            text = movie.title,
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
                text = "${movie.rating}",
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
    input: IFeedViewModelInput,
    thumbnailMovie: MovieFeedItemEntity
) {
    Card(
        onClick = {
            input.openDetail(thumbnailMovie.title)
        }
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = thumbnailMovie.thumbUrl)
                    .listener(
//                        onStart = { Timber.tag("0526").d("이미지 로드 중") },
//                        onSuccess = { _, _ -> Timber.tag("0526").d("이미지 로드 성공") },
//                        onError = { request, throwable ->  Timber.tag("0526").e(throwable.toString(), "이미지 로드 실패: ${request.data}") }
                    )
                    .apply {
                        crossfade(true)
                        scale(Scale.FILL)
                    }.build()
            ),
            modifier = Modifier
                .width(CARD_WIDTH)
                .height(200.dp),
            contentDescription = "Movie Poster Image"
        )
//        Timber.tag("ImageLoad").d(thumbnailMovie.thumbUrl)
    }
}