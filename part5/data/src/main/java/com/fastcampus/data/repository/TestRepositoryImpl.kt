package com.fastcampus.data.repository

import com.fastcampus.data.datasource.TestDataSource
import com.fastcampus.data.model.toDomainModel
import com.fastcampus.domain.model.TestModel
import com.fastcampus.domain.repository.TestRepository

class TestRepositoryImpl(
    val dataSource: TestDataSource
) : TestRepository {
    override fun getTestData(): TestModel {
        return dataSource.getTestModelResponse().toDomainModel()
    }
}