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
import com.example.hatlytask.movieScreen.util.setList
import kotlin.properties.Delegates


class MovieAdapter(private var movieInterface: MovieInterface) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var moviesList: MutableList<MoviesListModel.Movie> by Delegates.observable(mutableListOf()) { _, old, new ->
        if (old != new) {
            viewState = AdapterViewType.NORMAL
            setList(old, new)
        }
    }

    fun setData(list: List<MoviesListModel.Movie>) {
        moviesList = list.toMutableList()
    }


    @AdapterViewType
    private var viewState: Int = AdapterViewType.NORMAL
        set(value) {
            if (field == value) return
            if (field == AdapterViewType.LOADING || field == AdapterViewType.ERROR) {
                notifyItemChanged(moviesList.size)
                return
            }
            when (value) {
                AdapterViewType.LOADING,
                AdapterViewType.ERROR -> {
                    notifyItemInserted(moviesList.size)
                }
                else -> {
                    notifyItemRemoved(moviesList.size)
                }
            }
            field = value


        }

    fun setState(@AdapterViewType viewState: Int) {
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
                // ErrorViewHolder(binding, AdapterViewType.LOADING, retry = movieInterface)
                ErrorViewHolder(binding, retry = movieInterface)
            }
            else -> {
                val binding = MovieItemLayout.inflate(layoutInflater, parent, false)
                ItemViewHolder(binding,movieInterface)
            }
        }
    }

    //function for size of  List
    override fun getItemCount(): Int {
        if (viewState == AdapterViewType.LOADING || viewState == AdapterViewType.ERROR) {
            return moviesList.size + 1
        }
        return moviesList.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == moviesList.size) {
            viewState
        } else AdapterViewType.NORMAL
    }


    class ItemViewHolder(
        private val binding: MovieItemLayout,val  movieInterface: MovieInterface
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MoviesListModel.Movie) {
            binding.model = item
            binding.root.setOnClickListener {movieInterface.getDetails(item)  }
        }
    }

    class ErrorViewHolder(
        binding: ErrorLayout,
        // val error: Throwable?,
        retry: MovieInterface
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            //   binding.error = error
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


