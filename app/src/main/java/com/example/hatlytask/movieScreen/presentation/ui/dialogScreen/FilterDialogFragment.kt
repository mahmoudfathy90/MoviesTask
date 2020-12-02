package com.example.hatlytask.movieScreen.presentation.ui.dialogScreen

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.hatlytask.R
import com.example.hatlytask.movieScreen.domain.FilterMovieType
import com.example.hatlytask.movieScreen.domain.base.MoviesScreenActions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_movie_list.*

class FilterDialogFragment: BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener  {

    var types =
        arrayOf(FilterMovieType.ALL, FilterMovieType.MOVIE, FilterMovieType.PERSON, FilterMovieType.TV)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_fragment_filter, container, false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleSpinner()

    }


    private fun handleSpinner(){

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        //list.smoothScrollToPosition(0)
        //movieListViewModel execute MoviesScreenActions.FilterByType(types[p2])
    }


}