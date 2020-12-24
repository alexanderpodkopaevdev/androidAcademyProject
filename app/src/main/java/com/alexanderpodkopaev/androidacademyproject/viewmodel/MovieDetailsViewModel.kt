package com.alexanderpodkopaev.androidacademyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.androidacademyproject.data.Actor
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie
    private var _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> = _actors

    fun fetchMovie(id: Int?) {
        viewModelScope.launch {
            _movie.postValue(findMovie(id))
            _actors.value = movie.value?.actors
        }
    }

    private suspend fun findMovie(movieId: Int?): Movie? {
        return repository.getMovies().find { it.id == movieId }
    }
}