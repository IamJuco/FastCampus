package com.fastcampus.part5.data.repository

import com.fastcampus.part5.data.datasource.TempDataSource
import com.fastcampus.part5.domain.model.TempModel
import com.fastcampus.part5.domain.repository.TempRepository
import javax.inject.Inject

class TempRepositoryImpl @Inject constructor(
    private val dataSource: TempDataSource
): TempRepository {
    override fun getTempModel(): TempModel {
        return dataSource.getTempModel()
    }
}