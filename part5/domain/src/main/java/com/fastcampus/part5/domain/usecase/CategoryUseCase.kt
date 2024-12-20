package com.fastcampus.part5.domain.usecase

import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    fun getCategories(): Flow<List<Category>> {
        return repository.getCategories()
    }
    fun getProductByCategory(category: Category): Flow<List<Product>> {
        return repository.getProductByCategory(category)
    }
}