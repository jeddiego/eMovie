package com.rappi.core.data.usecaseimplementations

import com.rappi.core.domain.usecaseabstraction.SplashGetConfigurationsUseCase
import com.rappi.core.data.utils.Result
import com.rappi.core.domain.datasourceabstraction.SplashLocalDataSource
import com.rappi.core.domain.datasourceabstraction.SplashRemoteDataSource
import javax.inject.Inject

class SplashGetConfigurationsUseCaseImpl @Inject constructor(
    private val remoteDataSource: SplashRemoteDataSource,
    private val localDataSource: SplashLocalDataSource
) : SplashGetConfigurationsUseCase {
    override suspend fun execute(): Boolean {
        val localLanguagesCount = localDataSource.languagesCount()
        val localGenresCount = localDataSource.genresCount()

        var syncCorrect = false

        if (localLanguagesCount == 0) {
            val languages = remoteDataSource.downloadLanguages()
            if (languages is Result.Success) {
                localDataSource.saveLanguages(languages.data)
                syncCorrect = true
            }
        } else {
            syncCorrect = true
        }

        if(localGenresCount == 0) {
            val genres = remoteDataSource.downloadGenres()
            if(genres is Result.Success) {
                localDataSource.saveGenres(genres.data)
                syncCorrect = true
            }
        } else {
            syncCorrect = true
        }

        return syncCorrect
    }
}