package com.alexanderpodkopaev.androidacademyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexanderpodkopaev.androidacademyproject.data.Movie
import com.alexanderpodkopaev.androidacademyproject.repo.MoviesRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var _movie = MutableLiveData<Movie>()
    var movie: LiveData<Movie> = _movie

    fun fetchMovie(id: Int?) {
        viewModelScope.launch {
            _movie.postValue(findMovie(id))
        }
    }

    private suspend fun findMovie(movieId: Int?): Movie? {
        return repository.getMovies().find { it.id == movieId }
    }
}