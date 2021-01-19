package com.alexanderpodkopaev.androidacademyproject.data.entity

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"])
data class MovieGenre(
    val movieId: Long,
    val genreId: Long
)