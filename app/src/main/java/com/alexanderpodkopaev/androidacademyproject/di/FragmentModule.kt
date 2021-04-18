package com.alexanderpodkopaev.androidacademyproject.di

import com.alexanderpodkopaev.androidacademyproject.ui.FragmentCalendar
import com.alexanderpodkopaev.androidacademyproject.ui.FragmentMoviesDetails
import com.alexanderpodkopaev.androidacademyproject.ui.FragmentMoviesList
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindFragmentMoviesList(): FragmentMoviesList

    @ContributesAndroidInjector
    abstract fun bindFragmentMoviesDetails(): FragmentMoviesDetails

    @ContributesAndroidInjector
    abstract fun bindFragmentCalendar(): FragmentCalendar


}