package com.onix.internship.survay.adapter

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.onix.internship.survay.R

@BindingAdapter("errorMessage")
fun TextInputLayout.errorMessage(errorState: Boolean) {
    error = if (errorState) {
        (context.getString(R.string.error_empty_fields))
    } else ""
}