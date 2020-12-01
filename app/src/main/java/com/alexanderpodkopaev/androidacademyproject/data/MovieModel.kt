package com.alexanderpodkopaev.androidacademyproject.data

data class MovieModel(
    val title: String,
    val storyline: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    val genre: String,
    val age: Int,
    val countReview: Int,
    val picture: Int,
    val length: Int,
    val actors: List<ActorModel>
)
