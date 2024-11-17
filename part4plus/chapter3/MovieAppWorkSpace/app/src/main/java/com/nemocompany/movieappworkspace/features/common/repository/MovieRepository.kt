package com.nemocompany.movieappworkspace.features.common.repository

import com.nemocompany.movieappworkspace.features.common.entity.CategoryEntity
import com.nemocompany.movieappworkspace.features.common.entity.EntityWrapper
import com.nemocompany.movieappworkspace.features.common.entity.MovieDetailEntity
import com.nemocompany.movieappworkspace.features.common.network.api.IMovieAppNetworkApi
import com.nemocompany.movieappworkspace.features.feed.data.FeedConstants
import com.nemocompany.movieappworkspace.features.feed.data.mapper.CategoryMapper
import com.nemocompany.movieappworkspace.features.feed.domain.enum.SortOrder
import com.nemocompany.movieappworkspace.library.network.model.ApiResponse
import com.nemocompany.movieappworkspace.library.storage.IStorage
import timber.log.Timber
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieNetworkApi: IMovieAppNetworkApi,
    private val storage: IStorage,
    private val categoryMapper: CategoryMapper
) : IMovieDataSource {
    override suspend fun getCategories(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return categoryMapper.mapFromResult(
            result = movieNetworkApi.getMovies(),
            extra = sortOrder
        )
    }

    override suspend fun getMovieDetail(movieName: String): MovieDetailEntity {
        return storage
            .get<List<MovieDetailEntity>>(FeedConstants.MOVIE_LIST_KEY)
            ?.single { it.title == movieName }
            ?: MovieDetailEntity()
    }
}