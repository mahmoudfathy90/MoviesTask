package com.example.hatlytask.movieScreen.domain


import com.example.hatlytask.movieScreen.data.service.reguest.ListRequestModel
import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import com.example.hatlytask.movieScreen.domain.base.MovieListResult
import com.example.hatlytask.movieScreen.util.Constants
import java.lang.Exception

import javax.inject.Inject

class InitListUseCase @Inject constructor(private val repository: IListRepository) {
    /**
     * function used execute the all movies from repository
     * @param requestModel [ListRequestModel]  model that contains parms
     * @return [MovieListResult] sealed class that indicated success or loading or error or empty
     */
    suspend fun execute(requestBuilder: ListRequestModel.() -> Unit = {}): MovieListResult {
        return try {
            val request = ListRequestModel(page = Constants.FIRST_PAGE).apply(requestBuilder)
            val model = repository.getAllMovies(request)
            if (model.movies.isEmpty()) {
                MovieListResult.Empty
            } else {
                MovieListResult.InitListResult(
                    model.movies,
                    model.isLastPage()
                )
            }
        } catch (ex: Exception) {
            MovieListResult.ErrorInitList(ex)
        }
    }
}