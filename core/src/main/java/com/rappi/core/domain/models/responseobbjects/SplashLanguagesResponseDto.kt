package com.test.core.domain.models.responseobbjects

import com.google.gson.annotations.SerializedName

data class SplashLanguagesResponseDto(
    @SerializedName("iso_639_1") val iso_639_1: String,
    @SerializedName("english_name") val englishName: String,
    val name: String? = null
)