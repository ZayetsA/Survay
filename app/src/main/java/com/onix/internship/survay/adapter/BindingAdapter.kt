package com.onix.internship.survay.adapter

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.onix.internship.survay.R
import com.onix.internship.survay.util.ErrorsCatcher

@BindingAdapter("errorMessage")
fun TextInputLayout.errorMessage(errorType: ErrorsCatcher) {
    error = when (errorType) {
        ErrorsCatcher.NO -> ""
        ErrorsCatcher.INCORRECT_LOGIN -> context.getString(R.string.invalid_login_error)
        ErrorsCatcher.INCORRECT_PASSWORD -> context.getString(R.string.invalid_password_error)
        ErrorsCatcher.EMPTY_FIELD -> context.getString(R.string.error_empty_fields)
        ErrorsCatcher.PASSWORD_MATCH_ERROR -> context.getString(R.string.match_passwords_error)
        ErrorsCatcher.EXISTING_LOGIN -> context.getString(R.string.error_existing_username)
        ErrorsCatcher.EASY_PASSWORD -> context.getString(R.string.short_password_error)
    }
}