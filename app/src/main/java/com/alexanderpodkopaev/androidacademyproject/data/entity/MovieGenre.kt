package com.alexanderpodkopaev.androidacademyproject.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["movieId", "genreId"], foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            childColumns = arrayOf("movieId"),
            parentColumns = arrayOf("mId"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = GenreEntity::class,
            childColumns = arrayOf("genreId"),
            parentColumns = arrayOf("gId")
        )
    ]
)
data class MovieGenre(
    val movieId: Int,
    val genreId: Int
)