package com.fastcampus.data.datasource

import com.fastcampus.data.model.TestModelResponse

class TestDataSource {
    fun getTestModelResponse() : TestModelResponse {
        return TestModelResponse("response")
    }
}