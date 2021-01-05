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
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchMovie() {
        if (movie.value == null) {
            viewModelScope.launch {
                _isLoading.value = true
                _movie.value = repository.getMovie(id)
                _actors.value = repository.getActors(id)
                _isLoading.value = false
            }
        }
    }
}