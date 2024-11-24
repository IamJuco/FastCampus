package com.fastcampus.part5.data.deserializer

import com.fastcampus.part5.domain.model.Banner
import com.fastcampus.part5.domain.model.BannerList
import com.fastcampus.part5.domain.model.BaseModel
import com.fastcampus.part5.domain.model.ModelType
import com.fastcampus.part5.domain.model.Product
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

// Abstract classes can't be instantiated! 라는 에러가 뜰거임
// 그 이유는 추상클래스 ( sealed, abstract등 )의 타입을 모르기 떄문에
// Deserializer 처리를 해줘야함
class BaseModelDeserializer : JsonDeserializer<BaseModel> {
    companion object {
        private const val TYPE = "type"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): BaseModel {
        val root = json?.asJsonObject
        val gson = GsonBuilder().create()
        val typeString = root?.get(TYPE)?.asString ?: ""
        val type = ModelType.valueOf(typeString)

        return when (type) {
            ModelType.BANNER -> {
                gson.fromJson(root, Banner::class.java)
            }

            ModelType.PRODUCT -> {
                gson.fromJson(root, Product::class.java)
            }

            ModelType.BANNER_LIST -> {
                gson.fromJson(root, BannerList::class.java)
            }
        }
    }
}