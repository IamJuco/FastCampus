package com.fastcampus.part5.model

import androidx.navigation.NavHostController
import com.fastcampus.part5.delegate.ProductDelegate
import com.fastcampus.part5.domain.model.Carousel
import com.fastcampus.part5.domain.model.Product

class CarouselVM(model: Carousel, private val productDelegate: ProductDelegate) :
    PresentationVM<Carousel>(model) {

    fun openCarouselProduct(navHostController: NavHostController, product: Product) {
        productDelegate.openProduct(navHostController, product)
        sendCarouselLog()
    }

    fun sendCarouselLog() {

    }
}