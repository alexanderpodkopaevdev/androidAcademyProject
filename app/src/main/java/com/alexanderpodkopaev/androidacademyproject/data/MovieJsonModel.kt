package com.alexanderpodkopaev.androidacademyproject.data

import androidx.annotation.Keep
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

fun MovieJsonModel.convertToModel(
    imageBaseUrl: String
): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        poster = imageBaseUrl + "original" + this.poster,
        backdrop = imageBaseUrl + "original" + this.backdrop,
        ratings = this.ratings,
        adult = this.adult,
        runtime = this.runtime,
        actors = emptyList(),
        genres = this.genres,
        voteCount = this.voteCount
    )
}
