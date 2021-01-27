package com.alexanderpodkopaev.androidacademyproject.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actors")
data class ActorEntity(
    @PrimaryKey
    val aId: Int,
    val name: String,
    val picture: String
)