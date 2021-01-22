package com.alexanderpodkopaev.androidacademyproject.data.entity

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"])
data class MovieGenre(
    val movieId: Int,
    val genreId: Int
)