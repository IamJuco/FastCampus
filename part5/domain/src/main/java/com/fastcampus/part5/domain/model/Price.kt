package com.fastcampus.part5.domain.model

data class Price(
    val originPrice: Int, // 기본 가격
    val finalPrice: Int, // 할인이 됐다면 그 상품의 최종 가격
    val salesStatus: SalesStatus // 상품의 상태 ( 세일중인지, 솔드아웃됐는지, 아무것도 아닌건지 등 )
)
