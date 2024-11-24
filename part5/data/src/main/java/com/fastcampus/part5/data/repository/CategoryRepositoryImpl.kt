package com.fastcampus.part5.data.repository

import com.fastcampus.part5.data.datasource.ProductDataSource
import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource
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

    override fun getProductByCategory(category: Category): Flow<List<Product>> {
        return dataSource.getProducts().map {  list ->
            // filterIsInstance -> 원하는 타입만 filter 거는 메서드
            list.filterIsInstance<Product>().filter { product ->
                product.category.categoryId == category.categoryId
            }
        }
    }
}