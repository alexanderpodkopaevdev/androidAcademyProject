package com.alexanderpodkopaev.androidacademyproject.data.json

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ActorJsonModel(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val picture: String?
)

@Keep
@Serializable
data class AllActorsData(
    val id: Int,
    @SerialName("cast")
    val actors: List<ActorJsonModel>
)