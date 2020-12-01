package com.example.hatlytask.movieScreen.presentation.ui


import androidx.lifecycle.ViewModel
import com.example.hatlytask.movieScreen.domain.ListUseCase

import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val useCase: ListUseCase) :
    ViewModel() {







    override fun onCleared() {
        super.onCleared()
    }


}