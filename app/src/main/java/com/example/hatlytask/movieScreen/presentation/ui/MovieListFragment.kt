package com.example.hatlytask.movieScreen.presentation.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.hatlytask.databinding.MovieLayout
import com.example.hatlytask.movieScreen.data.service.response.MoviesListModel
import com.example.hatlytask.movieScreen.domain.base.MoviesScreenActions
import com.example.hatlytask.movieScreen.presentation.BaseFragmentWithInjector
import com.example.hatlytask.movieScreen.presentation.ui.dialogScreen.FilterActions
import com.example.hatlytask.movieScreen.util.MovieInterface
import com.example.hatlytask.movieScreen.util.RecyclerPaginator
import kotlinx.android.synthetic.main.fragment_movie_list.*


class MovieListFragment : BaseFragmentWithInjector(), MovieInterface {


    private val movieAdapter by lazy { MovieAdapter(this) }


    private val movieListViewModel: MovieListViewModel by viewModels()

    private lateinit var binding: MovieLayout
    private lateinit var paginator: RecyclerPaginator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieLayout.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = movieListViewModel
            lifecycleOwner = viewLifecycleOwner
            adapter = movieAdapter
        }
        init()
        goToDialog()
        handleBackResult()
        error.setOnClickListener {
            movieListViewModel execute MoviesScreenActions.InitMoviesList
        }
    }

    fun filterByDate(date:String){
        movieListViewModel execute  MoviesScreenActions.FilterByReleaseData("2020-08-22")
    }



    private fun handleBackResult(){
        val navController = findNavController();

          val navBackStackEntry = navController.getBackStackEntry(com.example.hatlytask.R.id.listScreen)

        // Create observer and add it to the NavBackStackEntry's lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME
                && navBackStackEntry.savedStateHandle.contains("key")
            ) {
                val result =
                    navBackStackEntry.savedStateHandle.get<String>("key")
//                if (result?.isType!!) {
//                    movieListViewModel execute MoviesScreenActions.FilterByType(result.type!!)
//                }else{
                    filterByDate(result!!)
            //    }
            }
        }
        navBackStackEntry.lifecycle.addObserver(observer)

        // As addObserver() does not automatically remove the observer, we
        // call removeObserver() manually when the view lifecycle is destroyed
        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                navBackStackEntry.lifecycle.removeObserver(observer)
            }
        })
    }


    override fun getFragmentVM(): Class<out ViewModel> {
        return MovieListViewModel::class.java
    }


    private fun goToDialog() {
        floating_action_button.setOnClickListener { fabView ->
            fabView.findNavController().navigate(
                MovieListFragmentDirections
                    .actionListScreenToFilterDialogFragment()
            )
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
        findNavController().navigate(
            MovieListFragmentDirections
                .actionListScreenToDetailsBottomSheetFragment(movie)
        )
    }


}




