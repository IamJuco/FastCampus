package com.fastcampus.data.model

import com.fastcampus.domain.model.TestModel

class TestModelResponse(
    val name: String
) {
}

fun TestModelResponse.toDomainModel() : TestModel? {
    if(name != null) {
        return TestModel(name)
    }
    return null
}