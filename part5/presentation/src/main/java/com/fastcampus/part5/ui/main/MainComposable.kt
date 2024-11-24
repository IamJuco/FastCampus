package com.fastcampus.part5.ui.main

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fastcampus.part5.domain.model.Banner
import com.fastcampus.part5.domain.model.BannerList
import com.fastcampus.part5.domain.model.ModelType
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.ui.component.BannerCard
import com.fastcampus.part5.ui.component.BannerListCard
import com.fastcampus.part5.ui.component.ProductCard
import com.fastcampus.part5.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel: MainViewModel) {
    val modelList by viewModel.modelList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(columnCount)
    ) {
        items(
            count = modelList.size,
            span = { index ->
                val item = modelList[index]
                val spanCount = getSpanCountByType(item.type, columnCount)
                GridItemSpan(spanCount)
            }
        ) {
            when (val item = modelList[it]) {
                is Banner -> BannerCard(model = item)
                is BannerList -> BannerListCard(model = item)
                is Product -> ProductCard(product = item) {
                }
            }
        }
    }
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when (type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER, ModelType.BANNER_LIST -> defaultColumnCount
    }
}