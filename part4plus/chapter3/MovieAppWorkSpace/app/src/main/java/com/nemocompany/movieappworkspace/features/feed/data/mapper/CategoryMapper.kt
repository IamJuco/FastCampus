package com.nemocompany.movieappworkspace.features.feed.data.mapper

import com.nemocompany.movieappworkspace.features.common.entity.CategoryEntity
import com.nemocompany.movieappworkspace.features.common.entity.EntityWrapper
import com.nemocompany.movieappworkspace.features.common.entity.MovieDetailEntity
import com.nemocompany.movieappworkspace.features.common.mapper.BaseMapper
import com.nemocompany.movieappworkspace.features.common.network.model.MovieResponse
import com.nemocompany.movieappworkspace.features.feed.data.FeedConstants
import com.nemocompany.movieappworkspace.features.feed.domain.enum.SortOrder
import com.nemocompany.movieappworkspace.library.storage.IStorage
import javax.inject.Inject

class CategoryMapper @Inject constructor(
    private val storage: IStorage
) : BaseMapper<List<MovieResponse>, List<CategoryEntity>>() {
    override fun getSuccess(
        model: List<MovieResponse>?,
        extra: Any?
    ): EntityWrapper.Success<List<CategoryEntity>> {
        return model?.let {
            EntityWrapper.Success(
                entity = mutableListOf<MovieDetailEntity>()
                    .apply {
                        addAll(model.map { it.toMovieDetailEntity() })
                    }
                    .also {
                        storage.save(FeedConstants.MOVIE_LIST_KEY, it)
                    }
                    .map {
                        it.toMovieThumbnailEntity()
                    }
                    .toCategoryList(if (extra is SortOrder) extra else SortOrder.RATING)
            )
        } ?: EntityWrapper.Success(
            entity = listOf()
        )
    }

    override fun getFailure(error: Throwable): EntityWrapper.Fail<List<CategoryEntity>> {
        return EntityWrapper.Fail(error)
    }
}