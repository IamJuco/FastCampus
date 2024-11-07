package com.nemocompany.chapter5.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nemocompany.chapter5.service.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

// 의존성 주입은 연결할 싱글턴 클래스가 필요한데 Hilt에선 제공을 해주니 이걸 쓰는거임
@InstallIn(SingletonComponent::class)
@Module
class AppModules {
    @Singleton
    @Provides
    @Named("API_URI")
    fun provideWebAPI(): String = "https://api.github.com/"

    @Singleton
    @Provides
    fun provideGson(): Gson =
        // LOWER_CASE_WITH_UNDERSCORES = Serializednamed 대신 사용 ( 객체가 _(언더바)가 될 수 있다는뜻 )
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Singleton
    @Provides
    fun provideConverterFactory(
        gson: Gson
    ): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("API_URI") apiUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun provideGithubService(
        retrofit: Retrofit
    ): GithubService = retrofit.create(GithubService::class.java)
}