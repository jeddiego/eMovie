package com.rappi.core.domain.datasourceabstraction

import com.rappi.core.domain.models.SplashGenresModel
import com.rappi.core.domain.models.SplashLanguagesModel

interface SplashLocalDataSource {
    fun saveLanguages(languages: List<SplashLanguagesModel>)
    fun getLanguagesByIsos(isos: List<String>): List<SplashLanguagesModel>
    fun languagesCount(): Int
    fun genresCount(): Int
    fun saveGenres(genres: List<SplashGenresModel>)
}