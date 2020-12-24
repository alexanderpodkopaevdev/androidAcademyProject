package com.alexanderpodkopaev.androidacademyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import kotlinx.coroutines.launch

class MoviesListViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>(emptyList())
    val moviesList: LiveData<List<Movie>> = _moviesList

    fun fetchMovies() {
        viewModelScope.launch {
            if (moviesList.value.isNullOrEmpty()) {
                val movies = repository.getMovies()
                _moviesList.value = movies
            }
        }
    }
}