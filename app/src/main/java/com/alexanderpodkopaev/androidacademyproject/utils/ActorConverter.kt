package com.alexanderpodkopaev.androidacademyproject.utils

import com.alexanderpodkopaev.androidacademyproject.data.entity.ActorEntity
import com.alexanderpodkopaev.androidacademyproject.data.json.ActorJsonModel
import com.alexanderpodkopaev.androidacademyproject.data.model.Actor

fun ActorJsonModel.convertToModel(imageBaseUrl: String): Actor {
    return Actor(
        id = this.id, name = this.name, picture = imageBaseUrl + "original" + this.picture
    )
}

fun ActorEntity.convertToModel(): Actor {
    return Actor(
        id = this.aId, name = this.name, picture = this.picture
    )
}

fun Actor.convertToEntityModel(): ActorEntity {
    return ActorEntity(
        aId = this.id, name = name, picture = picture
    )
}