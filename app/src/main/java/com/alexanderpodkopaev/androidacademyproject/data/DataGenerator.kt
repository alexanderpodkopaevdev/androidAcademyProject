package com.alexanderpodkopaev.androidacademyproject.data

import com.alexanderpodkopaev.androidacademyproject.R

fun getMovies(): List<MovieModel> {
    return listOf<MovieModel>(
        MovieModel(
            title = "Avengers: End Game",
            storyline = "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
            genre = "Action, Adventure, Fantasy",
            age = 13,
            countReview = 125,
            picture = R.drawable.picture_avengers,
            length = 137,
            actors = getActor()
        ),
        MovieModel(
            title = "Tenet",
            genre = "Action, Sci-Fi, Thriller",
            age = 16,
            countReview = 98,
            picture = R.drawable.picrute_tenet,
            length = 97,
            actors = getActor()
        ),
        MovieModel(
            title = "Black Widow",
            genre = "Action, Adventure, Sci-Fi",
            age = 13,
            countReview = 38,
            picture = R.drawable.picture_black,
            length = 102,
            actors = getActor()
        ),
        MovieModel(
            title = "Wonder Woman 1984",
            genre = "Action, Adventure, Fantasy",
            age = 13,
            countReview = 74,
            picture = R.drawable.picture_wonder,
            length = 120,
            actors = getActor()
        )
    )
}

fun getActor(): List<ActorModel> {
    return listOf<ActorModel>(
        ActorModel("Robert Downey Jr.", R.drawable.robertdowney),
        ActorModel("Chris Evans", R.drawable.chrisevans),
        ActorModel("Mark Ruffalo", R.drawable.markruffalo),
        ActorModel("Chris Hemsworth", R.drawable.chrishemsworth)
    )
}