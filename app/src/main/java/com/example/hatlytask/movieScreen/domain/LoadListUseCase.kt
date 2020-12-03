package com.example.hatlytask.movieScreen.domain


import com.example.hatlytask.movieScreen.data.service.reguest.ListRequestModel
import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import com.example.hatlytask.movieScreen.domain.base.MovieListResult
import java.lang.Exception

import javax.inject.Inject

class LoadListUseCase @Inject constructor(private val repository: IListRepository) {
    /**
     * function used execute the all movies from repository
     * @param requestModel [ListRequestModel]  model that contains parms
     * @return [MovieListResult] sealed class that indicated success or loading or error or empty
     */
    suspend fun execute(requestBuilder: ListRequestModel.() -> Unit): MovieListResult {
        val requestModel = ListRequestModel().apply(requestBuilder)
        return try {
            val model = repository.getAllMovies(requestModel)

            MovieListResult.LoadMoreListResult(
                model.movies,
                model.isLastPage()
            )

        } catch (ex: Exception) {
            MovieListResult.ErrorLoadMoreList(ex)
        }
    }
}