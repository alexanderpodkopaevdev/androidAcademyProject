package com.alexanderpodkopaev.androidacademyproject.data

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesJsonModel(
    val id: Int,
/*    val title: String,
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
    val voteCount: Int*/
)

/*fun MoviesJsonModel.convertToModel(
    genres: List<Genre>,
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
        genres = genres,
        voteCount = this.voteCount
    )
}*/

@Keep
@Serializable
data class AllMoviesData(
    val page: Int,
    @SerialName("results")
    val movies: List<MoviesJsonModel>,
    val total_pages: Int,
    val total_results: Int
)