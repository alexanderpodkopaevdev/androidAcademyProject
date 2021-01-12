package com.alexanderpodkopaev.androidacademyproject.data

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
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

@Keep
@Serializable
data class AllActorsData(
    val id: Int,
    @SerialName("cast")
    val actors: List<ActorJsonModel>
)