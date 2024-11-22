package com.fastcampus.part5.domain.repository

import com.fastcampus.part5.domain.model.Product

interface MainRepository {
    fun getProductList() : List<Product>
}