package com.fastcampus.part5.ui.main

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fastcampus.part5.ui.common.ProductCard
import com.fastcampus.part5.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel: MainViewModel) {
    val productList by viewModel.productList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(columnCount)
    ) {
        items(productList.size) {
            ProductCard(product = productList[it]) {
                // 상세화면 개발시 추가

            }
        }
    }
}