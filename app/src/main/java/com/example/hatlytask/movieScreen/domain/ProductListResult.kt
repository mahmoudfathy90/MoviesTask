package com.example.hatlytask.movieScreen.domain

import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel


sealed class ProductListResult {
    data class Success(val data: List<MoviesListModel.Movie>) : ProductListResult()
    object Loading : ProductListResult()
    object LoadingMore : ProductListResult()
    data class Error(val throwable: Throwable?) : ProductListResult()
    data class ErrorLoadMore(val throwable: Throwable?) : ProductListResult()
    object Empty : ProductListResult()
}