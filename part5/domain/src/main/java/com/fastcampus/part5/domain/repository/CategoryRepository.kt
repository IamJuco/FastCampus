package com.fastcampus.part5.domain.repository

import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
    fun getProductByCategory(category: Category): Flow<List<Product>>
}