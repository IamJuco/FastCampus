package com.nemocompany.chapter4.ui.third

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage

data class CardData(
    val imageUrl: String,
    val imageDescription: String,
    val author: String,
    val description: String
)

@Composable
fun ConstraintLayoutHardExample(cardData: CardData) {
    val placeHolder = Color(0x33000000)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (profileImage, author, description) = createRefs()
            AsyncImage(
                model = cardData.imageUrl,
                contentDescription = cardData.imageDescription,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(color = placeHolder),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .constrainAs(profileImage) {
                        centerVerticallyTo(parent)
                        start.linkTo(parent.start, margin = 8.dp)
                    }
            )
            Text(text = cardData.author, modifier = Modifier.constrainAs(author) {
                // 이렇게 linkTo를 바로 사용할 수 있음
                linkTo(profileImage.end, parent.end, startMargin = 8.dp, endMargin = 8.dp)
            })
            Text(text = cardData.description, modifier = Modifier.constrainAs(description) {
                linkTo(profileImage.end, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                width = Dimension.fillToConstraints
            })
            // chainStyle의 Packed = 컴포넌트 사이의 공간 여백을 없애줌
            val chain = createVerticalChain(author, description, chainStyle = ChainStyle.Packed)

            constrain(chain) {
                top.linkTo(parent.top, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
        }
    }
}