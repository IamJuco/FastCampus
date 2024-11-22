package com.fastcampus.part5.di

import com.fastcampus.part5.data.repository.MainRepositoryImpl
import com.fastcampus.part5.data.repository.TempRepositoryImpl
import com.fastcampus.part5.domain.repository.MainRepository
import com.fastcampus.part5.domain.repository.TempRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTempRepository(tempRepositoryImpl: TempRepositoryImpl): TempRepository

    @Binds
    @Singleton
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}