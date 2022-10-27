package com.rappi.core.domain.datasourceabstraction

import com.rappi.core.data.utils.RemoteDataSource
import com.rappi.core.domain.models.MoviesModel
import com.rappi.core.domain.models.MoviesTrailerModel

interface MoviesRemoteDataSource: RemoteDataSource {
    suspend fun getMoviesByListType(page: Int, listType: Int): com.rappi.core.data.utils.Result<List<MoviesModel>>
    suspend fun getTrailers(movieId: Long): com.rappi.core.data.utils.Result<List<MoviesTrailerModel>>
}