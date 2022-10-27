package com.rappi.core.data.datasourceimplementations

import com.rappi.core.domain.models.SplashGenresModel
import com.rappi.core.domain.models.SplashLanguagesModel
import com.rappi.core.data.services.SplashService
import com.rappi.core.data.utils.API_KEY
import com.rappi.core.data.utils.API_LANGUAGE
import com.rappi.core.data.utils.Result
import com.rappi.core.data.utils.asSplashLanguagesModel
import com.rappi.core.domain.datasourceabstraction.SplashRemoteDataSource
import javax.inject.Inject

class SplashRemoteDataSourceImpl @Inject constructor(
    private val service: SplashService
) : SplashRemoteDataSource {
    override suspend fun downloadLanguages(): Result<List<SplashLanguagesModel>> {
        return safeApiCall {
            service.downloadCatalogLanguagesAsync(API_KEY)
                .map { it.asSplashLanguagesModel() }
        }
    }

    override suspend fun downloadGenres(): Result<List<SplashGenresModel>> {
        return safeApiCall {
            service.downloadCatalogGenresAsync(API_KEY, API_LANGUAGE)
                .genres
                .map { SplashGenresModel(id = it.id, name = it.name) }
        }
    }
}