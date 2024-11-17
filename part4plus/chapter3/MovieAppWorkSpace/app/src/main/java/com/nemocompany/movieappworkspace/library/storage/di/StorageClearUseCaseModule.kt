package com.nemocompany.movieappworkspace.library.storage.di

import com.nemocompany.movieappworkspace.library.storage.usecases.IStorageClearUseCase
import com.nemocompany.movieappworkspace.library.storage.usecases.StorageClearUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageClearUseCaseModule {

    @Binds
    @Singleton
    abstract fun bindStorageClearUseCase(
        storageClearUserCase: StorageClearUseCase
    ): IStorageClearUseCase
}