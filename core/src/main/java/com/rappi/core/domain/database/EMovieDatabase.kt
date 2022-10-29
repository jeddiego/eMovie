package com.rappi.core.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rappi.core.data.daos.GenresDao
import com.rappi.core.data.daos.LanguagesDao
import com.rappi.core.data.daos.MoviesDao
import com.rappi.core.domain.entities.GenresEntity
import com.test.core.domain.entities.LanguagesEntity
import com.test.core.domain.entities.MoviesEntity

@Database(
    entities = [
        MoviesEntity::class,
        LanguagesEntity::class,
        GenresEntity::class
               ],
    version = 1
)
abstract class EMovieDatabase: RoomDatabase() {
    abstract fun MoviesDao(): MoviesDao
    abstract fun LanguagesDao(): LanguagesDao
    abstract fun GenresDao(): GenresDao
}