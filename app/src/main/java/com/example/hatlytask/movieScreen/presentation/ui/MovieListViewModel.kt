package com.example.hatlytask.movieScreen.presentation.ui


import androidx.lifecycle.*
import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import com.example.hatlytask.movieScreen.domain.InitListUseCase
import com.example.hatlytask.movieScreen.domain.LoadListUseCase
import com.example.hatlytask.movieScreen.domain.base.MovieListResult
import com.example.hatlytask.movieScreen.domain.base.MoviesListState
import com.example.hatlytask.movieScreen.domain.base.MoviesScreenActions
import com.example.hatlytask.movieScreen.util.Constants

import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val useCaseInit: InitListUseCase,
    private val useCaseLoadMore: LoadListUseCase
) :
    ViewModel() {


    private val actionReceiver = MutableLiveData<MoviesScreenActions>()
    private val defaultState = MoviesListState()
    val viewState: LiveData<MoviesListState> =
        actionReceiver.switchMap {
        handle(it)
    }.map {
        reduce(it)
    }.distinctUntilChanged()

    private fun reduce(it: MovieListResult): MoviesListState =
        it.reduce(defaultState, viewState.value ?: defaultState)


    private fun handle(action: MoviesScreenActions): LiveData<MovieListResult> = liveData {
        when (action) {
            is MoviesScreenActions.InitMoviesList -> {
                emit(MovieListResult.Loading)
                emit(useCaseInit.execute())
            }
            is MoviesScreenActions.LoadMoreList -> {
                emit(MovieListResult.LoadingMore)
                emit(useCaseLoadMore.execute {
                    page = action.page
                })
            }
            is MoviesScreenActions.FilterByType -> {
                emit(MovieListResult.Loading)
                emit(useCaseInit.execute() {
                    mediaType = action.type
                    page = Constants.FIRST_PAGE
                })
            }


            is MoviesScreenActions.FilterByReleaseData -> {
                emit(MovieListResult.FilterByDateResult(action.date))
            }
        }
    }


    infix fun execute(action: MoviesScreenActions) {
        actionReceiver.value = action
    }

    fun isLoadMoreDisabled(): Boolean =
        viewState.value?.isLoadMoreDisabled() ?: true


}