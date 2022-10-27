package com.rappi.core.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rappi.core.domain.models.MoviesModel
import com.test.core.domain.entities.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert(onConflict = REPLACE)
    fun insertMovie(movie: MoviesEntity)

    @Insert
    suspend fun insertMovies(movies: List<MoviesEntity>)

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getById(movieId: Long): Flow<MoviesEntity>

    @Query("select id, movies.original_title name, overview, languages.name language, poster posterPath, vote_average voteAverage, release_date releaseDate, trailer_youtube_key trailerId from movies join languages on original_language = iso_639_1 WHERE list_type = :listType")
    fun getAllByListType(listType: Int): Flow<List<MoviesModel>>

    @Query("SELECT COUNT(id) FROM movies WHERE list_type = :listType")
    suspend fun moviesCountByListType(listType: Int): Int
}