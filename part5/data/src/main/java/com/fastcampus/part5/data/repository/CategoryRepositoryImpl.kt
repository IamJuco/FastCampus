package com.fastcampus.part5.data.repository

import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(

) : CategoryRepository {
    override fun getCategories(): Flow<List<Category>> = flow {
        emit(
            listOf(
                Category.Top,
                Category.Bag,
                Category.Dress,
                Category.Pants,
                Category.Shoes,
                Category.FashionAccessories,
                Category.Outerwear,
                Category.Skirt
            )
        )
    }
}