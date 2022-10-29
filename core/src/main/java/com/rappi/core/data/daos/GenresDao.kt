package com.rappi.core.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rappi.core.domain.entities.GenresEntity

@Dao
interface GenresDao {

    @Insert(onConflict = REPLACE)
    fun insert(genres: List<GenresEntity>)

    @Insert(onConflict = REPLACE)
    fun insert(genre: GenresEntity)

    @Query("SELECT COUNT(id) FROM genres")
    fun countAll(): Int

}