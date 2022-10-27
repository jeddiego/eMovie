package com.rappi.core.data.services

import com.rappi.core.BuildConfig.CORE_API_VERSION
import com.test.core.domain.models.responseobbjects.MoviesResponseDto
import com.test.core.domain.models.responseobbjects.MoviesVideosResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("/${CORE_API_VERSION}/movie/upcoming")
    suspend fun listUpcomingMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): MoviesResponseDto

    @GET("/${CORE_API_VERSION}/movie/top_rated")
    suspend fun listTopRatedMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): MoviesResponseDto

    @GET("/$CORE_API_VERSION/movie/{movieId}/videos")
    suspend fun listVideosByMovieAsync(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String
    ): MoviesVideosResponseDto
}