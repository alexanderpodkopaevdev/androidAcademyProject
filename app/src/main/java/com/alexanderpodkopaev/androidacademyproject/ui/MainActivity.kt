package com.alexanderpodkopaev.androidacademyproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.alexanderpodkopaev.androidacademyproject.R
import com.alexanderpodkopaev.androidacademyproject.service.WorkRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(WorkRepository.WORK_TAG, ExistingPeriodicWorkPolicy.KEEP, WorkRepository().constrainedRequest)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.flFragment, FragmentMoviesList())
                .commit()
        }
    }

}