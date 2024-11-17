package com.nemocompany.movieappworkspace.features.detail.domain.usecase

import com.nemocompany.movieappworkspace.features.common.entity.MovieDetailEntity
import com.nemocompany.movieappworkspace.features.common.repository.IMovieDataSource
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val dataSource: IMovieDataSource
) : IGetMovieDetailUseCase {
    override suspend fun invoke(name: String): MovieDetailEntity {
        return dataSource.getMovieDetail(name)
    }
}