package com.alexanderpodkopaev.androidacademyproject.repo

import android.content.Context
import com.alexanderpodkopaev.androidacademyproject.data.Actor

class AssertsActorsRepo(val repo: MoviesRepository) : ActorsRepository {
    override suspend fun getActors(id: Int): List<Actor> {
        return repo.getMovie(id).actors
    }
}