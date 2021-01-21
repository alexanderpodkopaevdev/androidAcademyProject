package com.alexanderpodkopaev.androidacademyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.androidacademyproject.data.model.Movie
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>(emptyList())
    val moviesList: LiveData<List<Movie>> = _moviesList

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchMovies() {
        if (moviesList.value.isNullOrEmpty()) {
            viewModelScope.launch {
                _isLoading.value = true
                val movies = repository.getMovies()
                _moviesList.value = movies
                _isLoading.value = false
            }
        }
    }
}