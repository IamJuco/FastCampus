package com.fastcampus.part5.domain.model

data class Product(
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: Price,
    val category: Category, // 해당 상품의 카테고리
    val shop: Shop,
    val isNew: Boolean, // 새로 생긴 상품인지 ( new 뱃지 )
    val isFreeShipping: Boolean // 무료뱃지인지 아닌지
)