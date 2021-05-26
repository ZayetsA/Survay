package com.onix.internship.survay.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.SurvayDatabase

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val database: SurvayDatabase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}