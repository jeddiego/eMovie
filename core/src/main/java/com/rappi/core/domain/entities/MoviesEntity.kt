package com.test.core.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MoviesEntity (
    @PrimaryKey(autoGenerate = true) val _id: Long = 0,
    @ColumnInfo(name = "id") val movieId: Long,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "vote_average") val voteAverage: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "list_type") val listType: Int,
    @ColumnInfo(name = "trailer_youtube_key") val trailerKey: String? = null
)