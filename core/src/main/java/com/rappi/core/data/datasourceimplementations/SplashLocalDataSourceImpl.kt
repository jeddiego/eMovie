package com.rappi.core.data.datasourceimplementations

import com.rappi.core.domain.database.EMovieDatabase
import com.rappi.core.domain.models.SplashLanguagesModel
import com.rappi.core.data.utils.asLanguagesEntity
import com.rappi.core.data.utils.asLanguagesModel
import com.rappi.core.domain.datasourceabstraction.SplashLocalDataSource
import com.rappi.core.domain.entities.GenresEntity
import com.rappi.core.domain.models.SplashGenresModel
import javax.inject.Inject

class SplashLocalDataSourceImpl @Inject constructor(
    private val room: EMovieDatabase
): SplashLocalDataSource {

    override fun saveLanguages(languages: List<SplashLanguagesModel>) {
        room.LanguagesDao().insertAll(languages.map { it.asLanguagesEntity() })
    }

    override fun getLanguagesByIsos(isos: List<String>): List<SplashLanguagesModel> =
        room.LanguagesDao().getByIsos(isos).map { it.asLanguagesModel() }

    override fun languagesCount(): Int = room.LanguagesDao().countAll()

    override fun genresCount(): Int = room.GenresDao().countAll()

    override fun saveGenres(genres: List<SplashGenresModel>) =
        room.GenresDao().insert(genres.map { GenresEntity(id = it.id, name = it.name) })

}