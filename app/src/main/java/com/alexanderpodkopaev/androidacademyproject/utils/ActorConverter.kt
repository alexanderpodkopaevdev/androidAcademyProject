package com.alexanderpodkopaev.androidacademyproject.utils

import com.alexanderpodkopaev.androidacademyproject.data.json.ActorJsonModel
import com.alexanderpodkopaev.androidacademyproject.data.model.Actor

fun ActorJsonModel.convertToModel(imageBaseUrl: String): Actor {
    return Actor(
        id = this.id, name = this.name, picture = imageBaseUrl + "original" + this.picture
    )
}