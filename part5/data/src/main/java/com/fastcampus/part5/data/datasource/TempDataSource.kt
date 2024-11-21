package com.fastcampus.part5.data.datasource

import com.fastcampus.part5.domain.model.TempModel
import javax.inject.Inject

class TempDataSource @Inject constructor(

) {
    fun getTempModel(): TempModel {
        return TempModel("testModel")
    }
}