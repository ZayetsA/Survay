package com.onix.internship.survay.adapter

import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
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

@BindingAdapter("backgroundChecked")
fun TextView.editBackground(isPressed: Boolean) {
    background = if (isPressed) {
        ResourcesCompat.getDrawable(resources, R.drawable.selected_option_bg, null)
    } else {
        ResourcesCompat.getDrawable(resources, R.drawable.default_option_bg, null)
    }
}

@BindingAdapter("isActiveButton")
fun Button.setupActive(userChoice: String?) {
    isEnabled = userChoice?.isNotEmpty() ?: false
}

@BindingAdapter("setResultText")
fun TextView.setResultText(result: String) {
    text = resources.getString(R.string.test_score_title, result)
}