package com.example.hatlytask.movieScreen.presentation.ui.dialogScreen

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.navArgs
import com.example.hatlytask.R
import com.example.hatlytask.movieScreen.domain.FilterMovieType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_filter.*
import kotlinx.android.synthetic.main.fragment_movie_list.spinner
import java.text.SimpleDateFormat
import java.util.*


class FilterDialogFragment : BottomSheetDialogFragment(),
    AdapterView.OnItemSelectedListener {

    lateinit var types :Array<String>

    lateinit var picker:DatePickerDialog
    private val navArg by navArgs<FilterDialogFragmentArgs>()
    lateinit var filterInterface:FilterInterface



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
        filterInterface=navArg.model.filterInterface
        types=arrayOf(
            getString(R.string.choose_type),
            FilterMovieType.ALL,
            FilterMovieType.MOVIE,
            FilterMovieType.PERSON,
            FilterMovieType.TV
        )
        handleSpinner()
        getReleaseDate()

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
        filterInterface.getType(types[p2])
       // findNavController().previousBackStackEntry?.savedStateHandle?.set("key", )
    }

    private fun getReleaseDate(){
        releaseDate.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)
            // date picker dialog
            picker = DatePickerDialog(
                requireContext(),
                OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    var stringDate="$year-${monthOfYear + 1}-$dayOfMonth"
                    val format = SimpleDateFormat("yyyy-MM-dd")
                    val date = format.parse(stringDate)
                    val lastDate: String = format.format(date)
                   releaseDate.setText(lastDate)
                   filterInterface.getReleaseDate(lastDate)
                    dismiss()
                    }, year, month, day
            )
            picker.show()
        }

    }


}