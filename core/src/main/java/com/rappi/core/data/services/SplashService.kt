package com.rappi.core.data.services

import com.rappi.core.BuildConfig.CORE_API_VERSION
import com.test.core.domain.models.responseobbjects.SplashGenresResponseDto
import com.test.core.domain.models.responseobbjects.SplashLanguagesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SplashService {

    @GET("/${CORE_API_VERSION}/configuration/languages")
    suspend fun downloadCatalogLanguagesAsync(
        @Query("api_key") apiKey: String
    ): List<SplashLanguagesResponseDto>

    @GET("/${CORE_API_VERSION}/genre/movie/list")
    suspend fun downloadCatalogGenresAsync(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): SplashGenresResponseDto

}