package com.fastcampus.part5.domain.usecase

import com.fastcampus.part5.domain.model.BaseModel
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun getModelList(): Flow<List<BaseModel>> {
        return mainRepository.getModelList()
    }
}