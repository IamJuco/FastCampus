package com.nemocompany.movieappworkspace.features.detail.presentation.output

import com.nemocompany.movieappworkspace.features.common.entity.MovieDetailEntity

sealed class MovieDetailState {
    data object Initial : MovieDetailState()
    class Main(val movieDetailEntity: MovieDetailEntity) : MovieDetailState()
}