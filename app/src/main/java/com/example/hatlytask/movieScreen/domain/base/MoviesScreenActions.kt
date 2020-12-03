package com.example.hatlytask.movieScreen.domain.base

import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import com.example.hatlytask.movieScreen.domain.FilterMovieType

sealed class MoviesScreenActions {
    object InitMoviesList : MoviesScreenActions()
    data class LoadMoreList(val page: Int) : MoviesScreenActions()
    data class FilterByType(@FilterMovieType val type: String) : MoviesScreenActions()
    data class FilterByReleaseData(val date: String) : MoviesScreenActions()
}

sealed class MovieListResult : Result {
    data class InitListResult(var list: List<MoviesListModel.Movie>, val isLastPage: Boolean) :
        MovieListResult() {
        override fun reduce(
            defaultState: MoviesListState,
            oldState: MoviesListState
        ): MoviesListState {
            return defaultState.copy(data = list)
        }
    }

    data class LoadMoreListResult(var list: List<MoviesListModel.Movie>, val isLastPage: Boolean) :
        MovieListResult() {
        override fun reduce(
            defaultState: MoviesListState,
            oldState: MoviesListState
        ): MoviesListState {
            return oldState.copy(
                data = oldState.data?.plus(list),
                loadingMore = false,
                errorLoadMore = null
            )
        }
    }

    data class ErrorInitList(val throwable: Throwable) : MovieListResult() {
        override fun reduce(
            defaultState: MoviesListState,
            oldState: MoviesListState
        ): MoviesListState {
            return defaultState.copy(error = throwable)
        }
    }

    data class ErrorLoadMoreList(val throwable: Throwable) : MovieListResult() {
        override fun reduce(
            defaultState: MoviesListState,
            oldState: MoviesListState
        ): MoviesListState {
            return oldState.copy(errorLoadMore = throwable, loadingMore = false)
        }
    }

    object Loading : MovieListResult() {
        override fun reduce(
            defaultState: MoviesListState,
            oldState: MoviesListState
        ): MoviesListState {
            return defaultState.copy(loading = true)
        }
    }

    object LoadingMore : MovieListResult() {
        override fun reduce(
            defaultState: MoviesListState,
            oldState: MoviesListState
        ): MoviesListState {
            return oldState.copy(loadingMore = true, errorLoadMore = null)
        }
    }

    object Empty : MovieListResult() {
        override fun reduce(
            defaultState: MoviesListState,
            oldState: MoviesListState
        ): MoviesListState {
            return defaultState.copy(empty = true)
        }
    }

     data class FilterByDateResult(var date:String) :
        MovieListResult() {
        override fun reduce(
            defaultState: MoviesListState,
            oldState: MoviesListState
        ): MoviesListState {
           val list=oldState.data.filter { it.releaseDate==date }
           val isEmpty = list.isEmpty()
            return defaultState.copy(
                data = list  ,
                empty = isEmpty
            )
        }
    }


}


data class MoviesListState(
    val empty: Boolean = false,
    val loading: Boolean = false,
    val error: Throwable? = null,
    val loadingMore: Boolean = false,
    val errorLoadMore: Throwable? = null,
    val data: List<MoviesListModel.Movie> = emptyList()
) {
    fun isLoadMoreDisabled(): Boolean {
        return loading || error != null || empty || loadingMore || errorLoadMore != null
    }
}


interface Result {
    fun reduce(defaultState: MoviesListState, oldState: MoviesListState): MoviesListState
}