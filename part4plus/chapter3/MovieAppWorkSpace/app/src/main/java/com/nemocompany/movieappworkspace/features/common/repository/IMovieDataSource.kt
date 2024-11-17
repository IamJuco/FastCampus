package com.nemocompany.movieappworkspace.features.common.repository

interface IMovieDataSource {
    suspend fun getMovieList()
}