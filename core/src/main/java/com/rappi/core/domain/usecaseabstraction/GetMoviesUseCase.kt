package com.rappi.core.domain.usecaseabstraction

import com.rappi.core.domain.models.MoviesModel
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    fun getMoviesByListType(listType: Int): Flow<List<MoviesModel>>
    fun getRecommendedMovies(): Flow<List<MoviesModel>>
}