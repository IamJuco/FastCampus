package com.fastcampus.part5.viewmodel.category

import androidx.lifecycle.ViewModel
import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: CategoryUseCase
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(listOf())
    val products: StateFlow<List<Product>> = _products

    suspend fun updateCategory(category: Category) {
        useCase.getProductByCategory(category).collectLatest {
            _products.emit(it)
        }
    }
    fun openProduct(product: Product) {

    }
}