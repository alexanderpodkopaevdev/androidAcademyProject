package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.BuildConfig
import com.alexanderpodkopaev.androidacademyproject.data.MoviesApi
import com.alexanderpodkopaev.androidacademyproject.data.model.Actor
import com.alexanderpodkopaev.androidacademyproject.utils.convertToModel

class ActorsRepoImpl(private val moviesApi: MoviesApi, private val dbDataSource: DBDataSource) :
    ActorsRepository {

    override suspend fun getActors(id: Int): List<Actor> {
        val actorsFromDB = dbDataSource.getActors(id)
        return if (actorsFromDB.isEmpty()) {
            val imageBaseUrl = moviesApi.getConfig(BuildConfig.API_KEY).images.base_url
            val actorsFromJson = moviesApi.getActors(id, BuildConfig.API_KEY).actors
            val actorsFromNetwork = actorsFromJson.map { actorJsonModel ->
                actorJsonModel.convertToModel(imageBaseUrl)
            }
            dbDataSource.insertActors(id, actorsFromNetwork)
            actorsFromNetwork
        } else {
            actorsFromDB
        }
    }
}