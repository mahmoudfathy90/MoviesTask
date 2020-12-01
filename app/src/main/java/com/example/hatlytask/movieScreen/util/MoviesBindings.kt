package com.example.hatlytask.movieScreen.util


import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import com.example.hatlytask.movieScreen.presentation.ui.MovieListState
import com.example.hatlytask.movieScreen.presentation.ui.MovieListViewModel

@BindingMethods(
    value = [
        BindingMethod(type = RecyclerView::class, attribute = "adapter", method = "setAdapter")
    ]
)
object MoviesBindings {
    @BindingAdapter("movieList", "viewState")
    @JvmStatic
    fun RecyclerView.setMovieList(
        movieList: PagedList<MoviesListModel.Movie>?,
        viewState: MovieListState?
    ) {
//        businessList?.let { (adapter as? ProductListAdapter)?.submitList(businessList) }
//        viewState?.let { (adapter as? ProductListAdapter)?.setState(viewState) }
    }




}