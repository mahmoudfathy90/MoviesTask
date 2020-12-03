package com.example.hatlytask.movieScreen.domain

import com.example.hatlytask.movieScreen.data.service.reguest.ListRequestModel
import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel


interface IListRepository {
    /**
     * function used to get  the list of movies from api service
     * @param requestModel [ListRequestModel]  model  that contains all param to use it  @Query
     * in ListApiService
     * @return MoviesListModel[MoviesListModel] model that have all data for movies
     */
    suspend fun getAllMovies(requestModel: ListRequestModel): MoviesListModel

}