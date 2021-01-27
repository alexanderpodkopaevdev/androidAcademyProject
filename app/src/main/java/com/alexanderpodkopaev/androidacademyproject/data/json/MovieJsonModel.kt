package com.alexanderpodkopaev.androidacademyproject.data.json

import androidx.annotation.Keep
import com.alexanderpodkopaev.androidacademyproject.data.model.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class MovieJsonModel(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val poster: String,
    @SerialName("backdrop_path")
    val backdrop: String,
    @SerialName("vote_average")
    val ratings: Float,
    val adult: Boolean,
    val runtime: Int,
    val genres: List<Genre>,
    @SerialName("vote_count")
    val voteCount: Int
)