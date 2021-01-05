package com.alexanderpodkopaev.androidacademyproject.data

import kotlinx.serialization.Serializable

@Serializable
data class AllMoviesData(
    val dates: Dates,
    val page: Int,
    val results: List<MovieJsonModel>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
data class Dates(
    val maximum: String,
    val minimum: String
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