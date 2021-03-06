package com.alexanderpodkopaev.androidacademyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.androidacademyproject.data.model.Actor
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie
import com.alexanderpodkopaev.androidacademyproject.repo.ActorsRepository
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val actorsRepository: ActorsRepository
) :
    ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie
    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> = _actors
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchMovie(id: Int) {
        if (movie.value == null) {
            viewModelScope.launch {
                _isLoading.value = true
                _movie.value = moviesRepository.getMovie(id)
                _actors.value = actorsRepository.getActors(id)
                _isLoading.value = false
            }
        }
    }
}