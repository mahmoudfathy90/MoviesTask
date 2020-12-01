package com.example.hatlytask.movieScreen.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IntDef
import androidx.recyclerview.widget.RecyclerView
import com.example.hatlytask.R
import com.example.hatlytask.databinding.ErrorLayout
import com.example.hatlytask.databinding.MovieItemLayout
import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import com.example.hatlytask.movieScreen.util.MovieInterface


class MovieAdapter(private var movieInterface: MovieInterface, private var moviesList: List<MoviesListModel.Movie>) :
       RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    private var viewState = MovieListState()
        set(value) {
            if (field == value) return
            if (value.loadingMore || value.errorLoadMore != null) {
                if (field.loadingMore || field.errorLoadMore != null) {
                    notifyItemChanged(moviesList.size)
                } else {
                    notifyItemInserted(moviesList.size)
                }
            } else {
                notifyItemRemoved(moviesList.size)
            }
            field = value
        }

    fun setState(viewState: MovieListState) {
        this.viewState = viewState
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            AdapterViewType.LOADING -> {
                val view = layoutInflater.inflate(R.layout.loading, parent, false)
                object : RecyclerView.ViewHolder(view) {}
            }
            AdapterViewType.ERROR -> {
                val binding = ErrorLayout.inflate(layoutInflater, parent, false)
                ErrorViewHolder(binding, viewState.errorLoadMore,retry = movieInterface)
            }
            else -> {
                val binding = MovieItemLayout.inflate(layoutInflater, parent, false)
                ItemViewHolder(binding)
            }
        }
    }

    //function for size of  List
    override fun getItemCount(): Int {
        if (viewState.loadingMore || viewState.errorLoadMore != null) {
            return moviesList.size + 1
        }
        return moviesList.size
    }

    internal fun updateListWithScrool(movies: List<MoviesListModel.Movie>, scrool: Boolean, position: Int) {
        if (scrool) {
            this.moviesList = movies
            notifyItemRangeInserted(position, moviesList.size - 1 - position)
        } else {
            this.moviesList = movies
            this.notifyDataSetChanged()
        }
    }





    override fun getItemViewType(position: Int): Int {
        return if (position == moviesList.size) {
            when {
                viewState.loadingMore -> {
                    AdapterViewType.LOADING
                }
                viewState.errorLoadMore != null -> {
                    AdapterViewType.ERROR
                }
                else -> {
                    AdapterViewType.NORMAL
                }
            }
        } else AdapterViewType.NORMAL
    }





    class ItemViewHolder(
        private val binding: MovieItemLayout
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MoviesListModel.Movie) {
            binding.model = item
        }
    }

    class ErrorViewHolder(
        binding: ErrorLayout,
        val error: Throwable?,
        retry: MovieInterface
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.error = error
            binding.handler = retry
        }
    }


    @IntDef(AdapterViewType.LOADING, AdapterViewType.ERROR, AdapterViewType.NORMAL)
    annotation class AdapterViewType {
        companion object {
            const val LOADING = 1
            const val ERROR = 2
            const val NORMAL = 3
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            if (itemCount - 1 > position) {
                val item: MoviesListModel.Movie = moviesList[position]
                holder.bind(item)
            }
        }
    }


}


