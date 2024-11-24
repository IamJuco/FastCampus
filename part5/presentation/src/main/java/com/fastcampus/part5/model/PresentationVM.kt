package com.fastcampus.part5.model

import com.fastcampus.part5.domain.model.BaseModel

sealed class PresentationVM<T : BaseModel>(val model: T) {
    // fun
}