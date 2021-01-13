package com.alexanderpodkopaev.androidacademyproject.data

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesJsonModel(
    val id: Int,
)


@Keep
@Serializable
data class AllMoviesData(
    val page: Int,
    @SerialName("results")
    val movies: List<MoviesJsonModel>,
    val total_pages: Int,
    val total_results: Int
)