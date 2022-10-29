package com.rappi.core.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenresEntity(
    @PrimaryKey val id: Int,
    val name: String
)
