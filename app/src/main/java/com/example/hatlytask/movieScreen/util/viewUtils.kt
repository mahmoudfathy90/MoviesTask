package com.example.hatlytask.movieScreen.util

import android.content.Context

import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.hatlytask.R
import com.squareup.picasso.Picasso

import retrofit2.HttpException


@BindingAdapter("bind:loadImage")
fun ImageView.loadImage(imgUrl: String?) {
    if (imgUrl.isNullOrEmpty()) return
    Picasso.get().load(imgUrl).into(this)
}




@BindingAdapter("bind:checkError")
fun TextView.checkError(throwable: Throwable?) {
    if (throwable is HttpException) {
        val exception: HttpException = throwable as HttpException
        text = when (exception.code()) {
            400 -> {
                context.getString(R.string.bad_request)
            }
            500 -> {
                context.getString(R.string.server_error)
            }
            else -> {
                context.getString(R.string.error)
            }
        }
    }
    else
    {
        text=context.getString(R.string.no_network_connection)
    }
}

fun View.hideKeyboard() {
    val imm: InputMethodManager? =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(windowToken, 0)
}