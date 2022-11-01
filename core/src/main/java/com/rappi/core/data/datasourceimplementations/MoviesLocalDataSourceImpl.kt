package com.rappi.core.data.datasourceimplementations

import com.rappi.core.domain.database.EMovieDatabase
import com.rappi.core.domain.models.MoviesModel
import com.rappi.core.data.utils.asMoviesEntity
import com.rappi.core.domain.datasourceabstraction.MoviesLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesLocalDataSourceImpl @Inject constructor(
    private val room: EMovieDatabase
): MoviesLocalDataSource {
    override fun getAllMoviesByListType(listType: Int): Flow<List<MoviesModel>> =
        room.MoviesDao().getAllByListType(listType)

    override suspend fun sizeByListType(listyType: Int): Int =
        room.MoviesDao().moviesCountByListType(listyType)

    override suspend fun saveMovies(movies: List<MoviesModel>, listyType: Int) {
        room.MoviesDao().insertMovies(movies = movies.map { it.asMoviesEntity(listyType) })
    }
}