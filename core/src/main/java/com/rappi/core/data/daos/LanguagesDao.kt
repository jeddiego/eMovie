package com.rappi.core.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.test.core.domain.entities.LanguagesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguagesDao {

    @Insert(onConflict = REPLACE)
    fun insertAll(languages: List<LanguagesEntity>)

    @Insert
    fun insert(language: LanguagesEntity)

    @Query("SELECT * FROM languages")
    fun getAll(): Flow<List<LanguagesEntity>>

    @Query("SELECT * FROM languages WHERE iso_639_1 IN (:isos)")
    fun getByIsos(isos: List<String>): List<LanguagesEntity>

    @Query("SELECT COUNT(iso_639_1) FROM languages")
    fun countAll(): Int

}