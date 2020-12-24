package com.alexanderpodkopaev.androidacademyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.androidacademyproject.data.Actor
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MoviesRepository, private val id: Int?) :
    ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie
    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> = _actors

    fun fetchMovie() {
        viewModelScope.launch {
            if (movie.value?.id != id) {
                _movie.value = findMovie(id)
                _actors.value = movie.value?.actors
            }
        }
    }

    private suspend fun findMovie(movieId: Int?): Movie? {
        return repository.getMovies().find { it.id == movieId }
    }
}