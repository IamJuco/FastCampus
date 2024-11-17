package com.nemocompany.movieappworkspace.features.common.network.api

import com.nemocompany.movieappworkspace.features.common.network.model.MovieResponse
import com.nemocompany.movieappworkspace.library.network.model.ApiResult

interface IMovieAppNetworkApi {
    suspend fun getMovies(): ApiResult<List<MovieResponse>>
}