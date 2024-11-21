package com.fastcampus.domain.repository

import com.fastcampus.domain.model.TestModel

interface TestRepository {
    fun getTestData() : TestModel
}