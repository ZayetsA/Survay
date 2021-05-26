package com.onix.internship.survay.ui.manager.tests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.SurvayDatabase

@Suppress("UNCHECKED_CAST")
class TestViewModelFactory(
    private val database: SurvayDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
            return TestViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}