package com.alexanderpodkopaev.androidacademyproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllMoviesData(
    val dates: Dates,
    val page: Int,
    @SerialName("results")
    val movies: List<MoviesJsonModel>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
data class Dates(
    val maximum: String,
    val minimum: String
)

@Serializable
data class AllActorsData(
    val id: Int,
    @SerialName("cast")
    val actors: List<ActorJsonModel>
)

@Serializable
data class AllGenreData(
    val genres: List<Genre>
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}

@Serializable
data class Configure(
    val images: Images
)

@Serializable
data class Images(
    val base_url: String
)