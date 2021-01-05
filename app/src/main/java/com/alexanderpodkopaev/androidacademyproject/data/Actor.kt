package com.alexanderpodkopaev.androidacademyproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Actor(
    val id: Int,
    val name: String,
    val picture: String
)