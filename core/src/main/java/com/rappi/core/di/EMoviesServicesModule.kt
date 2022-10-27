package com.rappi.core.di

import com.rappi.core.data.services.MoviesService
import com.rappi.core.data.services.SplashService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EMoviesServicesModule {
    @Singleton
    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)

    @Singleton
    @Provides
    fun providesSplashService(retrofit: Retrofit): SplashService =
        retrofit.create(SplashService::class.java)
}