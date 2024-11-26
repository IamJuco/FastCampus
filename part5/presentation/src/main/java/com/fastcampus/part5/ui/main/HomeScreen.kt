package com.fastcampus.part5.ui.main

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.fastcampus.part5.domain.model.ModelType
import com.fastcampus.part5.model.BannerListVM
import com.fastcampus.part5.model.BannerVM
import com.fastcampus.part5.model.CarouselVM
import com.fastcampus.part5.model.ProductVM
import com.fastcampus.part5.model.RankingVM
import com.fastcampus.part5.ui.component.BannerCard
import com.fastcampus.part5.ui.component.BannerListCard
import com.fastcampus.part5.ui.component.CarouselCard
import com.fastcampus.part5.ui.component.ProductCard
import com.fastcampus.part5.ui.component.RankingCard
import com.fastcampus.part5.viewmodel.MainViewModel

@Composable
fun MainHomeScreen(navHostController: NavHostController,viewModel: MainViewModel) {
    val modelList by viewModel.modelList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(columnCount)
    ) {
        items(
            count = modelList.size,
            span = { index ->
                val item = modelList[index]
                val spanCount = getSpanCountByType(item.model.type, columnCount)
                GridItemSpan(spanCount)
            }
        ) {
            when (val item = modelList[it]) {
                is BannerVM -> BannerCard(presentationVM = item)
                is BannerListVM -> BannerListCard(presentationVM = item)
                is ProductVM -> ProductCard(presentationVM = item, navHostController = navHostController)
                is CarouselVM -> CarouselCard(presentationVM = item, navHostController = navHostController)
                is RankingVM -> RankingCard(presentationVM = item, navHostController = navHostController)
            }
        }
    }
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when (type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER,
        ModelType.BANNER_LIST,
        ModelType.CAROUSEL,
        ModelType.RANKING -> defaultColumnCount
    }
}