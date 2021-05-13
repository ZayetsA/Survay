package com.onix.internship.survay.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.RegisterRepository

@Suppress("UNCHECKED_CAST")
class SignUpViewModelFactory(
    private val repository: RegisterRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}