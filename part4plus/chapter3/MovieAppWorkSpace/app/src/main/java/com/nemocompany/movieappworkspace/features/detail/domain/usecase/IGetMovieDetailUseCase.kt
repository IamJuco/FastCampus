package com.nemocompany.movieappworkspace.features.detail.domain.usecase

import com.nemocompany.movieappworkspace.features.common.entity.MovieDetailEntity

interface IGetMovieDetailUseCase {
    suspend operator fun invoke(name: String): MovieDetailEntity
}