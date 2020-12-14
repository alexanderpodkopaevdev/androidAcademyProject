package com.alexanderpodkopaev.androidacademyproject.data

data class Genre(val id: Int, val name: String) {
    override fun toString(): String {
        return name
    }
}