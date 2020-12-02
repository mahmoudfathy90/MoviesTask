package com.example.hatlytask.movieScreen.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerPaginator(
    recycler: RecyclerView,
    val isLoadmoreDisabled: () -> Boolean = { true },
    val loadMore: (Int) -> Unit
) : RecyclerView.OnScrollListener() {
    /*
    * variable used to define the number of threshold items to call load more
    * so if there is two items to end of list the load more will called to load the next page items
    */
    private var threshold = 2

    /*
    * variable used to define the current page to fetch data
    */
    internal var currentPage = 1
        get() = field

    /*
    * Initialize scroll listener and attach it to recycler view
    */
    init {
        recycler.addOnScrollListener(this)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager
        layoutManager?.let {
            val visibleItemCount = it.childCount
            val totalItemCount = it.itemCount
            val firstVisiblePosition = when (layoutManager) {
                is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
                is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
                else -> return
            }
            if (isLoadmoreDisabled()) return
            if ((visibleItemCount + firstVisiblePosition + threshold) >= totalItemCount) {
                loadMore(++currentPage)
            }
        }
    }

    /**
     * Function used to reset current page to first page
     */
    fun resetCurrentPage() {
        this.currentPage = 1
    }


}