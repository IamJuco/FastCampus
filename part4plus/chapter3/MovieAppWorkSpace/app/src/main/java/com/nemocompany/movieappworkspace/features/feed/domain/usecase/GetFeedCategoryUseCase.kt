package com.nemocompany.movieappworkspace.features.feed.domain.usecase

import com.nemocompany.movieappworkspace.features.common.entity.CategoryEntity
import com.nemocompany.movieappworkspace.features.common.entity.EntityWrapper
import com.nemocompany.movieappworkspace.features.common.repository.IMovieDataSource
import com.nemocompany.movieappworkspace.features.feed.domain.enum.SortOrder
import javax.inject.Inject

class GetFeedCategoryUseCase @Inject constructor(
    private val dataSource: IMovieDataSource
) : IGetFeedCategoryUseCase {
    override suspend fun invoke(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return dataSource.getCategories(sortOrder)
    }
}