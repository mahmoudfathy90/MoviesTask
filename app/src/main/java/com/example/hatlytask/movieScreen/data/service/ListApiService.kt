package com.example.hatlytask.movieScreen.data.service

import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ListApiService {

    /**
     * function used get the list of movies from backend
     * @param page [Int]  number of page for  list of returned movies results .
     * @param time_window [String]  time for trending data such as day or week  .
     * @param media_type [String]  type of pages such as all , movie  .
     * @param api_key [String]  api key from movie site   .
     * @return MoviesListModel  that have all data about movies    .
     */
    @GET("trending/{media_type}/{time_window}")
    suspend fun getAllMovies(
        @Path("media_type") media_type: String?,
        @Path("time_window") time_window: String?,
        @Query("page") page: Int?,
        @Query("api_key") api_key: String?
    ): MoviesListModel

}