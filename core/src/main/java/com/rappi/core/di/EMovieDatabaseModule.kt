package com.rappi.core.di

import android.content.Context
import androidx.room.Room
import com.rappi.core.domain.database.EMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object EMovieDatabaseModule {

    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext appContext: Context): EMovieDatabase =
        Room.databaseBuilder(
            appContext,
            EMovieDatabase::class.java,
            "eMovieDatabase"
        ).build()
}