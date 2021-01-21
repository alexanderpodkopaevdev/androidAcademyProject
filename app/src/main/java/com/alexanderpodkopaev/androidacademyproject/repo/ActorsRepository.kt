package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.model.Actor

interface ActorsRepository {
    suspend fun getActors(id: Int): List<Actor>
}