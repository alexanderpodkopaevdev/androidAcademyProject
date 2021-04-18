package com.alexanderpodkopaev.androidacademyproject.di

import com.alexanderpodkopaev.androidacademyproject.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindMainActivity(): MainActivity

}