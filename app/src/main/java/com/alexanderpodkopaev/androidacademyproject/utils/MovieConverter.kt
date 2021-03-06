package com.alexanderpodkopaev.androidacademyproject.utils

import com.alexanderpodkopaev.androidacademyproject.data.entity.GenreEntity
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieEntity
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieWithGenres
import com.alexanderpodkopaev.androidacademyproject.data.json.MovieJsonModel
import com.alexanderpodkopaev.androidacademyproject.data.model.Genre
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie

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

fun MovieWithGenres.convertToModel(): Movie {
    return Movie(
        id = this.movie.mId,
        title = this.movie.title,
        overview = this.movie.overview,
        poster = this.movie.poster,
        backdrop = this.movie.backdrop,
        ratings = this.movie.ratings,
        adult = this.movie.adult,
        runtime = this.movie.runtime,
        actors = emptyList(),
        genres = this.genres.map { genreEntity -> genreEntity.convertToModel() },
        voteCount = this.movie.voteCount
    )
}

fun Movie.convertToEntityModel(): MovieEntity {
    return MovieEntity(
        mId = this.id,
        title = this.title,
        overview = this.overview,
        poster = this.poster,
        backdrop = this.backdrop,
        ratings = this.ratings,
        adult = this.adult,
        runtime = this.runtime,
        voteCount = this.voteCount
    )
}

fun GenreEntity.convertToModel(): Genre {
    return Genre(id = this.gId, name = this.name)
}

fun Genre.convertToEntityModel(): GenreEntity {
    return GenreEntity(gId = this.id, name = this.name)
}