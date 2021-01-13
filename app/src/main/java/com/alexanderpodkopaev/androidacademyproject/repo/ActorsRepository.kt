package com.alexanderpodkopaev.androidacademyproject.repo

import com.alexanderpodkopaev.androidacademyproject.data.Actor

interface ActorsRepository {
    suspend fun getActors(id: Int) : List<Actor>
}