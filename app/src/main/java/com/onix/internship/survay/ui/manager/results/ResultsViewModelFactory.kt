package com.onix.internship.survay.ui.manager.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.SurvayDatabase

@Suppress("UNCHECKED_CAST")
class ResultsViewModelFactory(
    private val database: SurvayDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java)) {
            return ResultsViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}