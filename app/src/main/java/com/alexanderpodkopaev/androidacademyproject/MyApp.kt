package com.alexanderpodkopaev.androidacademyproject

import android.app.Application
import android.content.Context
import com.alexanderpodkopaev.androidacademyproject.di.AppContainer

class MyApp : Application() {

    lateinit var context: Context
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        container = AppContainer(context)
    }
}