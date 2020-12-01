package com.example.hatlytask.movieScreen.domain



import com.example.hatlytask.movieScreen.data.service.reguest.ListRequestModel
import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import java.lang.Exception

import javax.inject.Inject

class ListUseCase @Inject constructor(private val repository: IListRepository)
{
    /**
     * function used execute the all movies from repository
     * @param requestModel [ListRequestModel]  model that contains parms
     * @return [ProductListResult] sealed class that indicated success or loading or error or empty
     */
    suspend fun execute(requestModel: ListRequestModel): ProductListResult {
        return try {
            val model = repository.getAllMovies(requestModel)
            if (model.movies?.isEmpty()!!){
                ProductListResult.Empty
            } else{
                ProductListResult.Success(model.movies as List<MoviesListModel.Movie>)
            }
        } catch (ex :Exception){
            ProductListResult.Error(ex)
        }
    }
}