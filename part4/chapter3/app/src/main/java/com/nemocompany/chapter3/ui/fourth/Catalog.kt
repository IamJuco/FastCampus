package com.nemocompany.chapter3.ui.fourth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nemocompany.chapter3.ItemData

@Composable
fun Item(itemData: ItemData) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = itemData.imageId),
                contentDescription = itemData.title
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = itemData.title, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = itemData.description)
        }
    }
}

@Composable
fun CatalogExample(itemList: List<ItemData>) {
    // LazyColumn, 기본적으로 세로
    // Recyclerview, ListView 대체 ( 필요할떄만 리스트 추가함 )
    LazyColumn {
        items(itemList) { item ->
            Item(item)
        }
    }
}