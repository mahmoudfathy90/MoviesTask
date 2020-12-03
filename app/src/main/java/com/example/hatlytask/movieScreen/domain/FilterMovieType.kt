package com.example.hatlytask.movieScreen.domain

import androidx.annotation.StringDef

@StringDef(FilterMovieType.MOVIE, FilterMovieType.ALL, FilterMovieType.TV, FilterMovieType.PERSON)
annotation class FilterMovieType {
    companion object {
        const val MOVIE = "movie"
        const val ALL = "all"
        const val TV = "tv"
        const val PERSON = "person"
    }
}
