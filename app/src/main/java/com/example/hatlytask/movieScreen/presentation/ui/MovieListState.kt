package com.example.hatlytask.movieScreen.presentation.ui

data class MovieListState (
        val loading: Boolean = false,
        val empty: Boolean = false,
        val error: Throwable? = null,
        val loadingMore: Boolean = false,
        val errorLoadMore: Throwable? = null
    )
