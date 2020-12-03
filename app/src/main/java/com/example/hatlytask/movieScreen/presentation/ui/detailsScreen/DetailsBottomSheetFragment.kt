package com.example.hatlytask.movieScreen.presentation.ui.detailsScreen

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs

import com.example.hatlytask.databinding.DetailsLayout

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailsBottomSheetFragment: BottomSheetDialogFragment() {


    private lateinit var binding: DetailsLayout
    private val navArg by navArgs<DetailsBottomSheetFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsLayout.inflate(inflater, container, false)
        binding.apply {
           model=navArg.model
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



}