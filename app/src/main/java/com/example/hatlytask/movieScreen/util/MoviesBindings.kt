package com.example.hatlytask.movieScreen.util


import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.recyclerview.widget.RecyclerView
import com.example.hatlytask.movieScreen.domain.base.MoviesListState
import com.example.hatlytask.movieScreen.presentation.ui.MovieAdapter

@BindingMethods(
    value = [
        BindingMethod(type = RecyclerView::class, attribute = "adapter", method = "setAdapter")
    ]
)
object MoviesBindings {
    @BindingAdapter("viewState")
    @JvmStatic
    fun RecyclerView.setMovieList(
        viewState: MoviesListState?
    ) {
        viewState?.data?.let { (adapter as? MovieAdapter)?.setData(it) }
        viewState?.let {
            val state: Int = when {
                it.loadingMore -> {
                    MovieAdapter.AdapterViewType.LOADING
                }
                it.errorLoadMore == null -> {
                    MovieAdapter.AdapterViewType.ERROR
                }
                else -> {
                    MovieAdapter.AdapterViewType.NORMAL
                }
            }
            (adapter as? MovieAdapter)?.setState(state)

//            if (it.loadingMore || it.errorLoadMore != null) {
//                if (field.loadingMore || field.errorLoadMore != null) {
//                    notifyItemChanged(moviesList.size)
//                } else {
//                    notifyItemInserted(moviesList.size)
//                }
//            } else {
//                notifyItemRemoved(moviesList.size)
//            }
        }
    }


}