package com.test.core.domain.models.responseobbjects

data class SplashGenresResponseDto(
    val genres: List<SplashGenresDto>
)

data class SplashGenresDto(
    val id: Int,
    val name: String
)
