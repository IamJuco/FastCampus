package com.fastcampus.part5.di

import com.fastcampus.part5.data.repository.TempRepositoryImpl
import com.fastcampus.part5.domain.repository.TempRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindTempRepository(tempRepositoryImpl: TempRepositoryImpl) : TempRepository
}