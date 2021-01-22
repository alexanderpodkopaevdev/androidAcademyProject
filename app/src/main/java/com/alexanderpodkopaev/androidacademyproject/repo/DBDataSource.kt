package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.MoviesDatabase
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieActor
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieGenre
import com.alexanderpodkopaev.androidacademyproject.data.model.Actor
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie
import com.alexanderpodkopaev.androidacademyproject.utils.convertToEntityModel
import com.alexanderpodkopaev.androidacademyproject.utils.convertToModel

class DBDataSource(private val db: MoviesDatabase) {

    private suspend fun insertMovie(movie: Movie) {
        db.moviesDao.insertMovie(movie.convertToEntityModel())
        for (genre in movie.genres) {
            db.moviesDao.insertGenre(genre.convertToEntityModel())
            db.moviesDao.insertMovieGenres(MovieGenre(movie.id.toLong(), genre.id.toLong()))
        }
    }

    private suspend fun insertActor(movieId: Int, actor: Actor) {
        db.actorsDao.insertActor(actor.convertToEntityModel())
        db.actorsDao.insertMovieActor(MovieActor(movieId, actor.id))
    }

    suspend fun getMovies(): List<Movie> {
        return db.moviesDao.getAllMoviesWithGenre().map { movieWithGenres ->
            movieWithGenres.convertToModel()
        }
    }

    suspend fun getMovie(id: Int): Movie? {
        return db.moviesDao.getMovieById(id.toLong())?.convertToModel()
    }

    suspend fun insertMovies(movies: List<Movie>) {
        for (movie in movies) {
            insertMovie(movie)
        }
    }

    suspend fun getActors(id: Int): List<Actor> {
        return db.actorsDao.getActorsById(id).actors.map { actorEntity ->
            actorEntity.convertToModel()
        }
    }

    suspend fun insertActors(movieId: Int, actors: List<Actor>) {
        for (actor in actors) {
            insertActor(movieId, actor)
        }
    }


}