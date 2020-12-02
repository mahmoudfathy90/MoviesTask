package com.example.hatlytask.movieScreen.data.service.reguest

import com.example.hatlytask.movieScreen.domain.FilterMovieType
import com.example.hatlytask.movieScreen.util.Constants


data class ListRequestModel(
    var page: Int?=Constants.FIRST_PAGE,
    @FilterMovieType var mediaType: String?=FilterMovieType.MOVIE,
    var timeWindow: String?=Constants.API_TIME_WINDOW
)

