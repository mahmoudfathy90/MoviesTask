package com.example.hatlytask.movieScreen.presentation.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hatlytask.databinding.MovieLayout
import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import com.example.hatlytask.movieScreen.domain.FilterMovieType
import com.example.hatlytask.movieScreen.domain.base.MoviesScreenActions
import com.example.hatlytask.movieScreen.presentation.BaseFragmentWithInjector
import com.example.hatlytask.movieScreen.util.MovieInterface
import com.example.hatlytask.movieScreen.util.RecyclerPaginator
import kotlinx.android.synthetic.main.fragment_movie_list.*


class MovieListFragment : BaseFragmentWithInjector(), MovieInterface{


    private val movieAdapter by lazy { MovieAdapter(this) }


    private val movieListViewModel: MovieListViewModel by viewModels()

    private lateinit var binding: MovieLayout
    private lateinit var paginator: RecyclerPaginator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieLayout.inflate(inflater, container, false)
        binding.apply {
            viewModel = movieListViewModel
            lifecycleOwner = viewLifecycleOwner
            adapter = movieAdapter
        }
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        goToDialog()

        error.setOnClickListener {
            movieListViewModel execute MoviesScreenActions.InitMoviesList
        }
    }


    override fun getFragmentVM(): Class<out ViewModel> {
        return MovieListViewModel::class.java
    }


    private fun goToDialog(){
        floating_action_button.setOnClickListener { fabView ->
            fabView.findNavController().navigate(MovieListFragmentDirections
                .actionListScreenToFilterDialogFragment())
        }
    }



    private fun init() {

        movieListViewModel execute MoviesScreenActions.InitMoviesList
        paginator = RecyclerPaginator(list, { movieListViewModel.isLoadMoreDisabled() }, {
            movieListViewModel execute MoviesScreenActions.LoadMoreList(it)
        })

    }


    override fun retry() {
        movieListViewModel execute MoviesScreenActions.LoadMoreList(paginator.currentPage)
    }

    override fun getDetails(movie: MoviesListModel.Movie) {
        TODO("Not yet implemented")
    }



}




