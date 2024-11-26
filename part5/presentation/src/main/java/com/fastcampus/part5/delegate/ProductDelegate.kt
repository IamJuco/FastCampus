package com.fastcampus.part5.delegate

import androidx.navigation.NavHostController
import com.fastcampus.part5.domain.model.Product

interface ProductDelegate {
    fun openProduct(navHostController: NavHostController, product: Product)
}