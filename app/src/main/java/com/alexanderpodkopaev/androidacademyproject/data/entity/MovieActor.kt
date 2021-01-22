package com.alexanderpodkopaev.androidacademyproject.data.entity

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "actorId"])
data class MovieActor(
    val movieId: Int,
    val actorId: Int
)