package com.nemocompany.chapter3.ui.second

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

data class CardData(
    val imageUrl: String,
    val imageDescription: String,
    val author: String,
    val description: String
)

@Composable
fun ProfileCardExample(cardData: CardData) {
    val placeHolderColor = Color(0x33000000)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = cardData.imageUrl,
                contentDescription = cardData.imageDescription,
                // clip(CircleShape) 이미지 둥글게 설정
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                // 이미지가 둥근 이미지가 아니라서, 둥글지 않은것은 짜르고 이미지를 보여줌
                contentScale = ContentScale.Crop,
                // 이미지 로딩 될때 보여줄 값 ( 지금은 컬러로 설정 )
                // 다른 이미지로 대체 가능
                placeholder = ColorPainter(placeHolderColor)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(text = cardData.author)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = cardData.description)
            }
        }
    }
}