package com.rappi.core.data.usecaseimplementations

import com.rappi.core.domain.database.EMovieDatabase
import com.rappi.core.domain.models.MoviesModel
import com.rappi.core.domain.usecaseabstraction.GetMoviesUseCase
import com.rappi.core.data.utils.LIST_TYPE_TOP_RATED
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(
    private val room: EMovieDatabase
): GetMoviesUseCase {

    override fun getMoviesByListType(listType: Int): Flow<List<MoviesModel>> =
        room.MoviesDao()
            .getAllByListType(listType)

    override fun getRecommendedMovies(): Flow<List<MoviesModel>> =
        room.MoviesDao()
            .getAllByListType(LIST_TYPE_TOP_RATED)
}