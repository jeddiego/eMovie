package com.test.core.domain.datasourceabstraction

import com.rappi.core.domain.models.SplashLanguagesModel

interface SplashLocalDataSource {
    fun saveLanguages(languages: List<SplashLanguagesModel>)
    fun getLanguagesByIsos(isos: List<String>): List<SplashLanguagesModel>
    fun languagesCount(): Int
}