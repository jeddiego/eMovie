package com.rappi.emovie

import com.rappi.core.data.utils.LIST_TYPE_TOP_RATED
import com.rappi.core.data.utils.Result
import com.rappi.core.domain.datasourceabstraction.MoviesRemoteDataSource
import com.rappi.core.domain.models.MoviesModel
import com.rappi.core.domain.models.MoviesTrailerModel
import com.rappi.core.domain.datasourceabstraction.MoviesLocalDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

val fakeMoviesUpcoming = listOf(
    MoviesModel(
        id = 1,
        name = "Title 1",
        posterPath = "poster1",
        voteAverage = "7.0",
        overview = "O1",
        trailerId = "11",
        language = "es",
        releaseDate = "2001"
    ),
    MoviesModel(
        id = 2,
        name = "Title 2",
        posterPath = "poster2",
        voteAverage = "7.0",
        overview = "O2",
        trailerId = "12",
        language = "es",
        releaseDate = "2002"
    ),
    MoviesModel(
        id = 3,
        name = "Title 3",
        posterPath = "poster3",
        voteAverage = "7.0",
        overview = "O3",
        trailerId = "13",
        language = "es",
        releaseDate = "2003"
    ),
    MoviesModel(
        id = 4,
        name = "Title 4",
        posterPath = "poster4",
        voteAverage = "7.0",
        overview = "O4",
        trailerId = "14",
        language = "es",
        releaseDate = "2004"
    )
)

val fakeMoviesTopRated = listOf(
    MoviesModel(
        id = 5,
        name = "Title 5",
        posterPath = "poster5",
        voteAverage = "7.0",
        overview = "O5",
        trailerId = "15",
        language = "es",
        releaseDate = "2005"
    ),
    MoviesModel(
        id = 6,
        name = "Title 6",
        posterPath = "poster6",
        voteAverage = "7.0",
        overview = "O6",
        trailerId = "16",
        language = "es",
        releaseDate = "2006"
    ),
    MoviesModel(
        id = 7,
        name = "Title 7",
        posterPath = "poster7",
        voteAverage = "7.0",
        overview = "O7",
        trailerId = "17",
        language = "es",
        releaseDate = "2007"
    ),
    MoviesModel(
        id = 8,
        name = "Title 8",
        posterPath = "poster8",
        voteAverage = "7.0",
        overview = "O8",
        trailerId = "18",
        language = "es",
        releaseDate = "2008"
    )
)

class FakeLocalDataSource : MoviesLocalDataSource {

    private val topRatedMovies = mutableListOf<MoviesModel>()
    private val upcomingMovies = mutableListOf<MoviesModel>()

    override fun getAllMoviesByListType(listType: Int): Flow<List<MoviesModel>> {
        return if(listType == LIST_TYPE_TOP_RATED) {
            flowOf(topRatedMovies)
        } else {
            flowOf(upcomingMovies)
        }
    }

    override suspend fun sizeByListType(listyType: Int): Int =
        if (listyType == LIST_TYPE_TOP_RATED) {
            topRatedMovies.size
        } else {
            upcomingMovies.size
        }

    override suspend fun saveMovies(movies: List<MoviesModel>, listyType: Int) {
        if (listyType == LIST_TYPE_TOP_RATED) {
            topRatedMovies += movies
        } else {
            upcomingMovies += movies
        }
    }

}

class FakeRemoteDataSource(
    private val topRatedMovies: List<MoviesModel> = emptyList(),
    private val upcomingMovies: List<MoviesModel> = emptyList(),
    private val trailerMovies: List<MoviesTrailerModel> = emptyList(),
    private val delay: Long = 0
) : MoviesRemoteDataSource {

    override suspend fun getMoviesByListType(page: Int, listType: Int): Result<List<MoviesModel>> {
        delay(delay)
        return if(listType == LIST_TYPE_TOP_RATED) {
            Result.Success(topRatedMovies)
        } else {
            Result.Success(upcomingMovies)
        }
    }

    override suspend fun getTrailers(movieId: Long): Result<List<MoviesTrailerModel>> {
        delay(delay)
        return Result.Success(trailerMovies)
    }
}
