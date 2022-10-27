package com.rappi.core.data.utils

import com.rappi.core.domain.models.SplashLanguagesModel
import com.test.core.domain.entities.LanguagesEntity
import com.test.core.domain.models.responseobbjects.SplashLanguagesResponseDto

fun SplashLanguagesResponseDto.asSplashLanguagesModel(): SplashLanguagesModel =
    SplashLanguagesModel(
        iso_639_1 = iso_639_1,
        englishName = englishName,
        name = name
    )

fun LanguagesEntity.asLanguagesModel() = SplashLanguagesModel(
    iso_639_1 = iso_639_1,
    englishName = englishName,
    name = name
)

fun SplashLanguagesModel.asLanguagesEntity() = LanguagesEntity(
    iso_639_1 = iso_639_1,
    englishName = englishName,
    name = name
)