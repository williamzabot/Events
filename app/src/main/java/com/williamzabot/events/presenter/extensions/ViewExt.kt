package com.williamzabot.events.presenter.extensions

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.williamzabot.events.R

fun AppCompatActivity.hideKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
}

fun ImageView.urlImage(url: String) =
    Glide.with(this)
        .load(GlideUrl(url))
        .error(R.drawable.nophoto)
        .into(this)