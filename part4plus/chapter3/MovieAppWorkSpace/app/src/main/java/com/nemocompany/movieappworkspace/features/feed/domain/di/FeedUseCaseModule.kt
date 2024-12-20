package com.nemocompany.movieappworkspace.features.feed.domain.di

import com.nemocompany.movieappworkspace.features.feed.domain.usecase.GetFeedCategoryUseCase
import com.nemocompany.movieappworkspace.features.feed.domain.usecase.IGetFeedCategoryUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeedUseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetMovieListUseCase(getMovieListUseCase: GetFeedCategoryUseCase): IGetFeedCategoryUseCase
}