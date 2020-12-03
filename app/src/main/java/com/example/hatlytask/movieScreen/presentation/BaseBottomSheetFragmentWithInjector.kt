package com.example.hatlytask.movieScreen.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.hatlytask.movieScreen.presentation.di.module.ViewModelFactory
import com.example.hatlytask.movieScreen.presentation.myApp.MyApplication
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


import javax.inject.Inject

abstract class BaseBottomSheetFragmentWithInjector : BottomSheetDialogFragment() {

    @Inject
    lateinit var factory: ViewModelFactory


    lateinit var vm: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApplication).networkComponent.inject(this)
         vm = ViewModelProviders.of(this, factory)[getFragmentVM()]
    }

    abstract fun getFragmentVM(): Class<out ViewModel>



}