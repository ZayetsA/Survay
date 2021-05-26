package com.onix.internship.survay.ui.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.SurvayDatabase

@Suppress("UNCHECKED_CAST")
class AdminViewModelFactory(
    private val database: SurvayDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminViewModel::class.java)) {
            return AdminViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}