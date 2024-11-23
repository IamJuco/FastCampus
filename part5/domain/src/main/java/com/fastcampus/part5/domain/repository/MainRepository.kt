package com.fastcampus.part5.domain.repository

import com.fastcampus.part5.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getProductList(): Flow<List<Product>>
}