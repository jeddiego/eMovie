package com.rappi.emovie.movies.presentation.models

data class MoviesFilterModel(
    val query: String,
    val isDate: Boolean = false
)
