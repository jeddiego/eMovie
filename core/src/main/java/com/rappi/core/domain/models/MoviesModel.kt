package com.rappi.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesModel(
    val id: Long,
    val name: String,
    val overview: String,
    val language: String,
    val posterPath: String,
    val voteAverage: String,
    val releaseDate: String,
    var trailerId: String? = null
): Parcelable
