package com.rappi.core.domain.datasourceabstraction

import com.rappi.core.data.utils.RemoteDataSource
import com.rappi.core.domain.models.SplashGenresModel
import com.rappi.core.domain.models.SplashLanguagesModel

interface SplashRemoteDataSource: RemoteDataSource {
    suspend fun downloadLanguages(): com.rappi.core.data.utils.Result<List<SplashLanguagesModel>>
    suspend fun downloadGenres(): com.rappi.core.data.utils.Result<List<SplashGenresModel>>
}