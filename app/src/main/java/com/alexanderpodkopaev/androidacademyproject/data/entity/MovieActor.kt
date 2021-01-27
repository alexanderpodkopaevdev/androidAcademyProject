package com.alexanderpodkopaev.androidacademyproject.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["movieId", "actorId"], foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            childColumns = arrayOf("movieId"),
            parentColumns = arrayOf("mId"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = ActorEntity::class,
            childColumns = arrayOf("actorId"),
            parentColumns = arrayOf("aId")
        )
    ]
)

data class MovieActor(
    val movieId: Int,
    val actorId: Int
)