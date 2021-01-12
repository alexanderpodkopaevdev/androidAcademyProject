package com.alexanderpodkopaev.androidacademyproject.utils

import com.alexanderpodkopaev.androidacademyproject.data.Actor
import com.alexanderpodkopaev.androidacademyproject.data.ActorJsonModel

fun ActorJsonModel.convertToModel(imageBaseUrl: String): Actor {
    return Actor(
        id = this.id, name = this.name, picture = imageBaseUrl + "original" + this.picture
    )
}