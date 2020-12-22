package com.alexanderpodkopaev.androidacademyproject.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.data.loadMovies
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesListViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var _moviesList = MutableLiveData<List<Movie>>(emptyList())
    var moviesList: LiveData<List<Movie>> = _moviesList

    fun fetchMovies() {
        viewModelScope.launch {
            val movies = repository.getMovies()
            withContext(Dispatchers.Main) {
                _moviesList.postValue(movies)
            }
        }
    }
}