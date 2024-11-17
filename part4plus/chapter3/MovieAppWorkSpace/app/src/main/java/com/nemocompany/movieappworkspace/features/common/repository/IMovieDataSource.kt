package com.nemocompany.movieappworkspace.features.common.repository

import com.nemocompany.movieappworkspace.features.common.entity.CategoryEntity
import com.nemocompany.movieappworkspace.features.common.entity.EntityWrapper
import com.nemocompany.movieappworkspace.features.common.entity.MovieDetailEntity
import com.nemocompany.movieappworkspace.features.feed.domain.enum.SortOrder

interface IMovieDataSource {
    suspend fun getCategories(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
    suspend fun getMovieDetail(movieName: String): MovieDetailEntity
}