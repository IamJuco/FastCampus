package com.fastcampus.part5.data.repository

import com.fastcampus.part5.data.datasource.ProductDataSource
import com.fastcampus.part5.domain.model.BaseModel
import com.fastcampus.part5.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource
) : MainRepository {
    override fun getModelList(): Flow<List<BaseModel>> {
        return dataSource.getProducts()
    }
}