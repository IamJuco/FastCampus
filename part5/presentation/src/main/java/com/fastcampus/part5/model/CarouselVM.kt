package com.fastcampus.part5.model

import com.fastcampus.part5.delegate.ProductDelegate
import com.fastcampus.part5.domain.model.Carousel
import com.fastcampus.part5.domain.model.Product

class CarouselVM(model: Carousel, private val productDelegate: ProductDelegate) :
    PresentationVM<Carousel>(model) {

    fun openCarouselProduct(product: Product) {
        productDelegate.openProduct(product)
        sendCarouselLog()
    }

    fun sendCarouselLog() {

    }
}