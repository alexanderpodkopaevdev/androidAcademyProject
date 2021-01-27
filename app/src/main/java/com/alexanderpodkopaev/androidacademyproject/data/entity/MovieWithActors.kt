package com.alexanderpodkopaev.androidacademyproject.data.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithActors(
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "mId",
        entity = ActorEntity::class,
        entityColumn = "aId",
        associateBy = Junction(
            value = MovieActor::class,
            parentColumn = "movieId",
            entityColumn = "actorId"
        )
    )
    val actors: List<ActorEntity>
)