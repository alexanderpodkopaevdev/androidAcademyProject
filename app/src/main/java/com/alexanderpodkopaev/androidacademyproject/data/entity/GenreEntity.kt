package com.alexanderpodkopaev.androidacademyproject.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey
    val gId: Int,
    val name: String
)