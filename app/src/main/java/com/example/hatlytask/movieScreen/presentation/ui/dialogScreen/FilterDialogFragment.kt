package com.example.hatlytask.movieScreen.presentation.ui.dialogScreen

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Filter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.hatlytask.R
import com.example.hatlytask.movieScreen.domain.FilterMovieType
import com.example.hatlytask.movieScreen.domain.base.MoviesScreenActions
import com.example.hatlytask.movieScreen.presentation.BaseBottomSheetFragmentWithInjector
import com.example.hatlytask.movieScreen.presentation.ui.MovieListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_filter.*
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.fragment_movie_list.spinner

class FilterDialogFragment : BottomSheetDialogFragment(),
    AdapterView.OnItemSelectedListener {

    lateinit var types :Array<String>

    lateinit var filterActions:FilterActions


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_fragment_filter, container, false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        return view
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filterActions= FilterActions()
        types=arrayOf(
            getString(R.string.choose_type),
            FilterMovieType.ALL,
            FilterMovieType.MOVIE,
            FilterMovieType.PERSON,
            FilterMovieType.TV
        )
        handleSpinner()
        handleData()

    }

    private fun handleData(){
        releaseDate.setOnClickListener {
            dismiss()
            filterActions.isType=false
            // filterActions.type=types[p2]
            findNavController().previousBackStackEntry?.savedStateHandle?.set("key", "date")

        }
    }


    private fun handleSpinner() {

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p2 == 0) return
        dismiss()
        filterActions.isType=true
        filterActions.type=types[p2]
        findNavController().previousBackStackEntry?.savedStateHandle?.set("key", types[p2])
    }


}