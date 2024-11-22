package com.fastcampus.part5.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.fastcampus.part5.data.db.converter.BasketConverter
import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.model.Price
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.model.Shop

// 장바구니
@Entity(tableName = "basket")
@TypeConverters(BasketConverter::class)
data class BasketProductEntity(
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: Price,
    val category: Category, // 해당 상품의 카테고리
    val shop: Shop,
    val isNew: Boolean, // 새로 생긴 상품인지 ( new 뱃지 )
    val isFreeShipping: Boolean // 무료뱃지인지 아닌지
)

fun BasketProductEntity.toDomainModel(): Product {
    return Product(
        productId = productId,
        productName = productName,
        imageUrl = imageUrl,
        price = price,
        category = category,
        shop = shop,
        isNew = isNew,
        isFreeShipping = isFreeShipping
    )
}