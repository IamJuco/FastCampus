package com.fastcampus.part5.delegate

import com.fastcampus.part5.domain.model.Product

interface ProductDelegate {
    fun openProduct(product: Product)
}