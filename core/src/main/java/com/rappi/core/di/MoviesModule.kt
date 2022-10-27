package com.rappi.core.di

import com.rappi.core.data.datasourceimplementations.MoviesLocalDataSourceImpl
import com.rappi.core.data.datasourceimplementations.MoviesRemoteDataSourceImpl
import com.rappi.core.data.usecaseimplementations.GetMoviesUseCaseImpl
import com.rappi.core.data.usecaseimplementations.MoviesCheckIfRequireNewPageUSeCaseImpl
import com.rappi.core.domain.datasourceabstraction.MoviesRemoteDataSource
import com.rappi.core.domain.usecaseabstraction.GetMoviesUseCase
import com.rappi.core.domain.usecaseabstraction.MoviesCheckIfRequireNewPageUseCase
import com.test.core.domain.datasourceabstraction.MoviesLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MoviesModule {

    @Provides
    fun providesMoviesCheckIfRequireNewPageUseCase(moviesCheckIfRequireNewPageUseCaseImpl: MoviesCheckIfRequireNewPageUSeCaseImpl):
            MoviesCheckIfRequireNewPageUseCase = moviesCheckIfRequireNewPageUseCaseImpl

    @Provides
    fun providesMoviesLocalDataSource(moviesLocalDataSourceImpl: MoviesLocalDataSourceImpl): MoviesLocalDataSource = moviesLocalDataSourceImpl

    @Provides
    fun providesMoviesNetworkDataSource(moviesRemoteDataSourceImpl: MoviesRemoteDataSourceImpl): MoviesRemoteDataSource = moviesRemoteDataSourceImpl

    @Provides
    fun providesMoviesUpcomingUseCase(moviesUpcomingUseCaseImpl: GetMoviesUseCaseImpl): GetMoviesUseCase = moviesUpcomingUseCaseImpl
}