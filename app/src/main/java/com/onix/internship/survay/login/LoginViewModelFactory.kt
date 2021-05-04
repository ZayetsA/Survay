package com.onix.internship.survay.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.RegisterRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val repository: RegisterRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}