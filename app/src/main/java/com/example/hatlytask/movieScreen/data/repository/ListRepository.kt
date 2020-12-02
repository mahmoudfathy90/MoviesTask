package com.example.hatlytask.movieScreen.data.repository

import com.example.hatlytask.movieScreen.data.service.ListApiService
import com.example.hatlytask.movieScreen.data.service.reguest.ListRequestModel
import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import com.example.hatlytask.movieScreen.domain.IListRepository
import com.example.hatlytask.movieScreen.util.Constants
import javax.inject.Inject


class ListRepository @Inject constructor(private val apiService: ListApiService) : IListRepository
{
    override suspend fun getAllMovies(requestModel: ListRequestModel): MoviesListModel {
        return apiService.getAllMovies(
            page = requestModel.page,time_window = requestModel.timeWindow,media_type = requestModel.mediaType
        ,api_key = Constants.API_KEY
        )
    }

}