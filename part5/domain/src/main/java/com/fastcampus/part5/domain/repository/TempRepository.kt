package com.fastcampus.part5.domain.repository

import com.fastcampus.part5.domain.model.TempModel

interface TempRepository {
    fun getTempModel() : TempModel
}