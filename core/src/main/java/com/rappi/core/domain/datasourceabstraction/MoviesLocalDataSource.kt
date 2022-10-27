package com.test.core.domain.datasourceabstraction

import com.rappi.core.domain.models.MoviesModel
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    fun getAllMoviesByListType(listType: Int): Flow<List<MoviesModel>>

    suspend fun sizeByListType(listyType: Int): Int

    suspend fun saveMovies(movies: List<MoviesModel>, listyType: Int)
}