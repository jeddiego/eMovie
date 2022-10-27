package com.rappi.core.data.usecaseimplementations

import com.rappi.core.domain.usecaseabstraction.MoviesCheckIfRequireNewPageUseCase
import com.rappi.core.data.utils.Result
import com.test.core.domain.datasourceabstraction.MoviesLocalDataSource
import com.rappi.core.domain.datasourceabstraction.MoviesRemoteDataSource
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

private const val PAGE_SIZE = 20
private const val PAGE_THRESHOLD = 4

class MoviesCheckIfRequireNewPageUSeCaseImpl @Inject constructor(
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
): MoviesCheckIfRequireNewPageUseCase {
    override suspend fun execute(lastVisible: Int, listType: Int) {
        val size = localDataSource.sizeByListType(listType)

        if (lastVisible >= size - PAGE_THRESHOLD) {
            val page = size / PAGE_SIZE + 1
            when(val newMovies = withTimeout(5000) {
                remoteDataSource.getMoviesByListType(page, listType)
            }) {
                is Result.Error -> false
                is Result.Success -> {
                    newMovies.data.forEach { movies ->
                        val videos = remoteDataSource.getTrailers(movies.id)
                        if(videos is Result.Success) {
                            val trailer = videos.data.filter { it.type == "Trailer" && it.site == "YouTube" }
                            if(trailer.isNotEmpty()) {
                                movies.trailerId = trailer[0].key
                            }
                        }
                    }
                    localDataSource.saveMovies(newMovies.data, listType)
                }
            }
        }
    }
}