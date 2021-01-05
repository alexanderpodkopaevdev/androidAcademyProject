package com.alexanderpodkopaev.androidacademyproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorJsonModel(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val picture: String = ""
)

fun ActorJsonModel.convertToModel(imageBaseUrl: String): Actor {
    return Actor(
        id = this.id, name = this.name, picture = imageBaseUrl + "original" + this.picture
    )
}