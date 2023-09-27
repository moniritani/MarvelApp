package com.monir.marvelapp.di.module

import com.monir.marvelapp.data.api.ApiHelper
import com.monir.marvelapp.data.api.ApiService
import com.monir.marvelapp.data.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = ApiHelper.buildMarvelService()

    @Singleton
    @Provides
    fun provideMarvelRepository(apiService: ApiService): MarvelRepository = MarvelRepository(apiService)
}
