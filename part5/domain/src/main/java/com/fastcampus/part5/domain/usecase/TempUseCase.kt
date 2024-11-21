package com.fastcampus.part5.domain.usecase

import com.fastcampus.part5.domain.model.TempModel
import com.fastcampus.part5.domain.repository.TempRepository
import javax.inject.Inject

class TempUseCase @Inject constructor(
    private val repository: TempRepository
) {
    fun getTempModel() : TempModel {
        return repository.getTempModel()
    }
}