package com.onix.internship.survay.ui.manager.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ManagerViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ManagerViewModel::class.java)) {
            return ManagerViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
