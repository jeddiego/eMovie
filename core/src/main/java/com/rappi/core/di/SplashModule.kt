package com.rappi.core.di

import com.rappi.core.data.datasourceimplementations.SplashLocalDataSourceImpl
import com.rappi.core.data.datasourceimplementations.SplashRemoteDataSourceImpl
import com.rappi.core.data.usecaseimplementations.SplashGetConfigurationsUseCaseImpl
import com.rappi.core.domain.datasourceabstraction.SplashRemoteDataSource
import com.rappi.core.domain.usecaseabstraction.SplashGetConfigurationsUseCase
import com.test.core.domain.datasourceabstraction.SplashLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object SplashModule {

    @Provides
    fun providesSplashGetConfigurationsUseCase(splashGetConfigurationsUseCaseImpl: SplashGetConfigurationsUseCaseImpl): SplashGetConfigurationsUseCase =
        splashGetConfigurationsUseCaseImpl

    @Provides
    fun providesSplashRemoteDataSource(splashRemoteDataSourceImpl: SplashRemoteDataSourceImpl): SplashRemoteDataSource =
        splashRemoteDataSourceImpl

    @Provides
    fun providesSplashLocalDataSource(splashLocalDataSourceImpl: SplashLocalDataSourceImpl): SplashLocalDataSource = splashLocalDataSourceImpl

}