package com.rappi.core.domain.models

data class MoviesTrailerModel(
    val movieId: Long,
    val key: String,
    val site: String,
    val isOfficial: Boolean,
    val type: String
)
