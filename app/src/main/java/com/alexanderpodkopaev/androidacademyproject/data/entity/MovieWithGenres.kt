package com.alexanderpodkopaev.androidacademyproject.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithGenres(
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "mId",
        entity = GenreEntity::class,
        entityColumn = "gId",
        associateBy = Junction(
            value = MovieGenre::class,
            parentColumn = "movieId",
            entityColumn = "genreId"
        )
    )
    val genres: List<GenreEntity>
)