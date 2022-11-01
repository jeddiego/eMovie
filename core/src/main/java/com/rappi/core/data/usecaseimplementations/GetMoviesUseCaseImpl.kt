package com.rappi.core.data.usecaseimplementations

import com.rappi.core.data.utils.LIST_TYPE_TOP_RATED
import com.rappi.core.domain.datasourceabstraction.MoviesLocalDataSource
import com.rappi.core.domain.models.MoviesModel
import com.rappi.core.domain.usecaseabstraction.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(
    private val localDataSource: MoviesLocalDataSource,
): GetMoviesUseCase {

    override fun getMoviesByListType(listType: Int): Flow<List<MoviesModel>> =
        localDataSource.getAllMoviesByListType(listType)

    override fun getRecommendedMovies(): Flow<List<MoviesModel>> =
        localDataSource.getAllMoviesByListType(LIST_TYPE_TOP_RATED)
}