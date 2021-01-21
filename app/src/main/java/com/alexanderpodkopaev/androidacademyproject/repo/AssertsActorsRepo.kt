package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.model.Actor

class AssertsActorsRepo(val repo: MoviesRepository) : ActorsRepository {

    override suspend fun getActors(id: Int): List<Actor> {
        return repo.getMovie(id).actors
    }
}