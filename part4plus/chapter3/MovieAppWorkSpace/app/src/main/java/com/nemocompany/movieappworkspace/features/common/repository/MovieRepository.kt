package com.nemocompany.movieappworkspace.features.common.repository

import com.nemocompany.movieappworkspace.features.common.network.api.IMovieAppNetworkApi
import com.nemocompany.movieappworkspace.library.network.model.ApiResponse
import timber.log.Timber
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieNetworkApi: IMovieAppNetworkApi
): IMovieDataSource {
    override suspend fun getMovieList() {
        val data = movieNetworkApi.getMovies().response
        if (data is ApiResponse.Success) {
            val movieList = data.data
        }
    }
}