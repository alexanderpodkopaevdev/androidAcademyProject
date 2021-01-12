package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.BuildConfig
import com.alexanderpodkopaev.androidacademyproject.data.Actor
import com.alexanderpodkopaev.androidacademyproject.data.MoviesApi
import com.alexanderpodkopaev.androidacademyproject.utils.convertToModel

class NetworkActorsRepo(private val moviesApi: MoviesApi): ActorsRepository {
    override suspend fun getActors(id: Int?) : List<Actor> {
        val imageBaseUrl = moviesApi.getConfig(BuildConfig.API_KEY).images.base_url
        val actorsFromJson = moviesApi.getActors(id, BuildConfig.API_KEY).actors
        return actorsFromJson.map { actorJsonModel ->
            actorJsonModel.convertToModel(imageBaseUrl)
        }
    }
}