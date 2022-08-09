package com.comercio.comercioapk.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Kotlin extensions
 */

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.injectViewModelActivity(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this.requireActivity(), factory).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory).get(T::class.java)
}

inline fun <reified T : ViewModel> DialogFragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(requireActivity(), factory).get(T::class.java)
}

fun ImageView.updateImageDrawable(@DrawableRes image: Int) {
    this.setImageDrawable(ContextCompat.getDrawable(this.context, image))
}

fun ImageView.updateColorFilter(@ColorRes color: Int) {
    this.setColorFilter(ContextCompat.getColor(this.context, color))
}

fun LinearLayout.updateBackgroundColor(@ColorRes color: Int) {
    this.setBackgroundColor(ContextCompat.getColor(this.context, color))
}

fun TextView.updateTextColor(@ColorRes color: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, color))
}

fun TextView.updateBackground(@DrawableRes image: Int) {
    this.background = ContextCompat.getDrawable(this.context, image)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}