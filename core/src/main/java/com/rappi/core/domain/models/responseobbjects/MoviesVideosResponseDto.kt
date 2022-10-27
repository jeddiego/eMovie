package com.test.core.domain.models.responseobbjects

data class MoviesVideosResponseDto(
    val results: List<MoviesVideoModelDto>
)

data class MoviesVideoModelDto(
    val movieId: Long,
    val key: String,
    val site: String,
    val type: String,
    val official: Boolean
)
