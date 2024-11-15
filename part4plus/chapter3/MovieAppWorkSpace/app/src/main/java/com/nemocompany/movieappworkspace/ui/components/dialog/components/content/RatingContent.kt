package com.nemocompany.movieappworkspace.ui.components.dialog.components.content

import android.content.res.ColorStateList
import android.widget.RatingBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import com.nemocompany.movieappworkspace.ui.models.dialog.DialogText
import com.nemocompany.movieappworkspace.ui.theme.Paddings
import com.nemocompany.movieappworkspace.ui.util.getAnnotatedText

@Composable
fun RatingContent(content: DialogText.Rating) {
    Column(
        modifier = Modifier.padding(
            top = Paddings.large,
            bottom = Paddings.xlarge
        )
    ) {
        RatingTable(
            rating = content.rating,
            movieTitle = content.text ?: ""
        )
    }
}

@Composable
fun ColumnScope.RatingTable(
    rating: Float,
    movieTitle: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
    ) {
        Text(
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            ),
            text = getAnnotatedText(text = movieTitle),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.secondary
            ),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(Paddings.large))
        StarRatingBar(
            score = rating
        )
    }
}

@Composable
fun ColumnScope.StarRatingBar(score: Float) {
    Card(
        modifier = Modifier
            .padding(
                Paddings.medium
            )
            .wrapContentSize()
            .align(Alignment.CenterHorizontally),
        elevation = CardDefaults.cardElevation(Paddings.none)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            val foregroundColor = MaterialTheme.colorScheme.primary.toArgb()
            val starBackgroundColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.2f
            ).toArgb()
            val ratingBackgroundColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.05f
            ).toArgb()

            // 기존 view를 JetpackCompose로 사용할 수 있게 해주는 메서드
            AndroidView(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.Center),
                // RatingBar가 JetpackCompose가 아님
                factory = {
                    RatingBar(
                        it
                    ).apply {
                        max = 10
                        stepSize = 25f
                        rating = score
                        numStars = 5
                        progressTintList = ColorStateList.valueOf(foregroundColor)
                        progressBackgroundTintList = ColorStateList.valueOf(starBackgroundColor)
                        setBackgroundColor(ratingBackgroundColor)
                    }
                },
            )
        }
    }
}