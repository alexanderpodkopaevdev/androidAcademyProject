package com.alexanderpodkopaev.androidacademyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.repo.DatabaseMoviesRepo
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesListViewModel(
    private val repository: MoviesRepository,
    private val dbRepository: DatabaseMoviesRepo
) : ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>(emptyList())
    val moviesList: LiveData<List<Movie>> = _moviesList

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchMovies() {
        if (moviesList.value.isNullOrEmpty()) {
            viewModelScope.launch {
                _isLoading.value = true
                //val movies = repository.getMovies()
                val movies = loadFromDb()
                //saveToDb(movies)
                _isLoading.value = false
                _moviesList.value = movies
            }
        }
    }

    private suspend fun loadFromDb(): List<Movie> {
        return withContext(Dispatchers.IO) {
             dbRepository.getMovies()
        }
    }

    private suspend fun saveToDb(movies: List<Movie>) {
        withContext(Dispatchers.IO) {
            for (movie in movies) {
                dbRepository.insertMovie(movie)
            }
        }
    }
}