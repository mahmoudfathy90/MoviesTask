package com.example.hatlytask.movieScreen.util

import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel


interface MovieInterface {
    fun retry()
    fun getDetails(movie:MoviesListModel.Movie)
}