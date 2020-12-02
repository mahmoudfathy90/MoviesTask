package com.example.hatlytask.movieScreen.util

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import com.example.hatlytask.movieScreen.presentation.ui.MovieAdapter

class DiffUtilsHelper(
    private val oldList: List<MoviesListModel.Movie>,
    private val newList: List<MoviesListModel.Movie>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id


    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}

fun MovieAdapter.setList(
    oldList: List<MoviesListModel.Movie>,
    newList: List<MoviesListModel.Movie>
) {
    val diffs = DiffUtilsHelper(oldList, newList)
    val result = DiffUtil.calculateDiff(diffs)
    result.dispatchUpdatesTo(this)
}