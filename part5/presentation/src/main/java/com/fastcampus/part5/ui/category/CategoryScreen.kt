package com.fastcampus.part5.ui.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.ui.component.ProductCard
import com.fastcampus.part5.viewmodel.category.CategoryViewModel

@Composable
fun CategoryScreen(
    category: Category,
    navHostController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    val products by viewModel.products.collectAsState()
    LaunchedEffect(key1 = category) {
        viewModel.updateCategory(category)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(products.size) { index ->
            ProductCard(
                presentationVM = products[index],
                navHostController = navHostController
            )
        }
    }
}