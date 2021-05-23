package com.onix.internship.survay.ui.manager.tests.new_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.SurvayDatabase

@Suppress("UNCHECKED_CAST")
class CreateTestViewModelFactory(
    private val database: SurvayDatabase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateTestViewModel::class.java)) {
            return CreateTestViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}