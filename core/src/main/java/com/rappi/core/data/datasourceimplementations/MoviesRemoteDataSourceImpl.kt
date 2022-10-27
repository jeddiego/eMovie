package com.rappi.core.data.datasourceimplementations

import com.rappi.core.domain.models.MoviesModel
import com.rappi.core.domain.models.MoviesTrailerModel
import com.rappi.core.data.services.MoviesService
import com.rappi.core.data.utils.*
import com.rappi.core.domain.datasourceabstraction.MoviesRemoteDataSource
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val service: MoviesService
) : MoviesRemoteDataSource {
    override suspend fun getMoviesByListType(page: Int, listType: Int): Result<List<MoviesModel>> {
        return safeApiCall {
            if (listType == LIST_TYPE_UPCOMING) {
                service
                    .listUpcomingMoviesAsync(API_KEY, page, API_LANGUAGE)
                    .results
                    .map { it.asMoviesModel() }
            } else {
                service
                    .listTopRatedMoviesAsync(API_KEY, page, API_LANGUAGE)
                    .results
                    .map { it.asMoviesModel() }
            }
        }
    }

    override suspend fun getTrailers(movieId: Long): Result<List<MoviesTrailerModel>> {
        return safeApiCall {
            service
                .listVideosByMovieAsync(movieId, API_KEY)
                .results.map { it.asMoviesTrailerModel(movieId) } }
    }

}