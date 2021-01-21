package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.MoviesDatabase
import com.alexanderpodkopaev.androidacademyproject.data.entity.MovieGenre
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

    suspend fun getMovies(): List<Movie> {
        return db.moviesDao.getAllMoviesWithGenre().map { movieWithGenres ->
            movieWithGenres.convertToModel()
        }
    }

    suspend fun getMovie(id: Int): Movie {
        TODO("Not yet implemented")
    }

    suspend fun insertMovies(movies: List<Movie>) {
        for (movie in movies) {
            insertMovie(movie)
        }
    }


}