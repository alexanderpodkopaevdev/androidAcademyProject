package com.alexanderpodkopaev.androidacademyproject.di.viewmodel

import androidx.lifecycle.ViewModel
import com.alexanderpodkopaev.androidacademyproject.viewmodel.CalendarViewModel
import com.alexanderpodkopaev.androidacademyproject.viewmodel.MovieDetailsViewModel
import com.alexanderpodkopaev.androidacademyproject.viewmodel.MoviesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    abstract fun moviesListViewModel(viewModel: MoviesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun movieDetailsViewModel(viewModel: MovieDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CalendarViewModel::class)
    abstract fun calendarViewModel(viewModel: CalendarViewModel): ViewModel
}