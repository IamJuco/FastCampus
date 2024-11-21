package com.fastcampus.part5.viewmodel

import androidx.lifecycle.ViewModel
import com.fastcampus.part5.domain.model.TempModel
import com.fastcampus.part5.domain.usecase.TempUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TempViewModel @Inject constructor(
    private val useCase: TempUseCase
) : ViewModel() {

    fun getTempModel(): TempModel {
        return useCase.getTempModel()
    }
}