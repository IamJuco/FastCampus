package com.fastcampus.part5.data.db.converter

import androidx.room.TypeConverter
import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.model.Price
import com.fastcampus.part5.domain.model.Shop
import com.google.gson.GsonBuilder

class BasketConverter {

    private val gson = GsonBuilder().create()

    // TypeConverter = 내가 만든 클래스 (Price)를 Room에 직접 저장할 수없다.
    // 따라서 Room에서 사용할 수 있는 형식(String)으로 변환한다고 Room에 알려줌
    // 즉, 현재 Entity의 타입이 내가 지정한 클래스이기 때문에 TypeConverter를 써야함

    // Price를 Json 문자열 으로 변환
    @TypeConverter
    fun fromPrice(value: Price): String {
        return gson.toJson(value)
    }

    // Json 문자열을 Price 객체로 변환
    @TypeConverter
    fun toPrice(value: String): Price {
        return gson.fromJson(value, Price::class.java)
    }

    @TypeConverter
    fun fromCategory(value: Category): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCategory(value: String): Category {
        return gson.fromJson(value, Category::class.java)
    }

    @TypeConverter
    fun fromShop(value: Shop): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toShop(value: String): Shop {
        return gson.fromJson(value, Shop::class.java)
    }
}