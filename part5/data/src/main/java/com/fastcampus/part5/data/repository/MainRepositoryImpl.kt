package com.fastcampus.part5.data.repository

import android.content.Context
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.repository.MainRepository
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStreamReader
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    // Hilt에서 의존성 주입을 처리할때 특정 Context 타입을 지정하기 위해 사용
    // Context가 여러종류 이기때문에 ApplicationContext라는 것을 알려줌
    @ApplicationContext private val context: Context
) : MainRepository {
    override fun getProductList(): List<Product> {
        val inputStream = context.assets.open("product_list.json")
        val inputStreamReader = InputStreamReader(inputStream)
        val jsonString = inputStreamReader.readText()
        val type = object : TypeToken<List<Product>>() { }.type

        return GsonBuilder().create().fromJson(jsonString, type)
    }
}