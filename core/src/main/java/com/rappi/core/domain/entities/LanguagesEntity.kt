package com.test.core.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "languages")
data class LanguagesEntity(
    @PrimaryKey val iso_639_1: String,
    @ColumnInfo(name = "english_name") val englishName: String,
    val name: String?
)
