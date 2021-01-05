package com.alexanderpodkopaev.androidacademyproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val runtime: Int = 0,
    @SerialName("genre_ids")
    val genres: List<Int>,
    @SerialName("vote_count")
    val voteCount: Int
)

fun MovieJsonModel.convertToModel(
    genres: List<Genre>
): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        poster = this.poster,
        backdrop = this.backdrop,
        ratings = this.ratings,
        adult = this.adult,
        runtime = this.runtime,
        genres = genres,
        voteCount = this.voteCount
    )
}
