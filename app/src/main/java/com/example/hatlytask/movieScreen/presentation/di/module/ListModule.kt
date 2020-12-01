package com.example.hatlytask.movieScreen.presentation.di.module




import androidx.lifecycle.ViewModel
import com.example.hatlytask.movieScreen.data.repository.ListRepository
import com.example.hatlytask.movieScreen.domain.IListRepository
import com.example.hatlytask.movieScreen.presentation.ui.MovieListViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
 abstract  class ListModule {


 @Binds
 abstract fun movieList(repository: ListRepository) : IListRepository




 @Binds
 @IntoMap
 @ViewModelKey(MovieListViewModel::class)
 internal abstract fun itemViewModel(viewModel: MovieListViewModel): ViewModel



}