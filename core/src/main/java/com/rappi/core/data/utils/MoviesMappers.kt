package com.rappi.core.data.utils

import com.rappi.core.domain.models.MoviesModel
import com.rappi.core.domain.models.MoviesTrailerModel
import com.test.core.domain.entities.MoviesEntity
import com.test.core.domain.models.responseobbjects.MovieDto
import com.test.core.domain.models.responseobbjects.MoviesVideoModelDto

fun MoviesEntity.asMoviesModel(): MoviesModel = MoviesModel(
    id = movieId,
    overview = overview,
    name = originalTitle,
    language = originalLanguage,
    posterPath = poster,
    voteAverage = voteAverage,
    releaseDate = releaseDate
)

fun MovieDto.asMoviesModel(): MoviesModel = MoviesModel(
    id = id,
    overview = overview,
    name = originalTitle,
    language = originalLanguage,
    posterPath = posterPath,
    voteAverage = voteAverage.toString(),
    releaseDate = releaseDate
)

fun MoviesModel.asMoviesEntity(listType: Int): MoviesEntity = MoviesEntity(
    movieId = id,
    overview = overview,
    originalTitle = name,
    originalLanguage = language,
    poster = posterPath,
    voteAverage = voteAverage,
    releaseDate = releaseDate,
    listType = listType,
    trailerKey = trailerId
)

fun MoviesVideoModelDto.asMoviesTrailerModel(movieId: Long): MoviesTrailerModel = MoviesTrailerModel(
    movieId = movieId,
    key = key,
    site = site,
    isOfficial = official,
    type = type
)