package com.example.hatlytask.movieScreen.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.hatlytask.databinding.MovieFragmentLayout
import com.example.hatlytask.movieScreen.presentation.BaseFragmentWithInjector
import com.example.hatlytask.movieScreen.util.MovieInterface




class MovieListFragment : BaseFragmentWithInjector(), MovieInterface {


   // private val productAdapter by lazy { MovieAdapter( this) }


    private val productListViewModel: MovieListViewModel by lazy {
        ViewModelProviders.of(this).get(MovieListViewModel::class.java)
    }

    private lateinit var binding: MovieFragmentLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieFragmentLayout.inflate(inflater, container, false)
        binding.apply {
            viewModel = productListViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun getFragmentVM(): Class<out ViewModel> {
        return MovieListViewModel::class.java
    }



    override fun retry() {

    }

}




