package com.onix.internship.survay.ui.student.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.SurvayDatabase

class ResultViewModelFactory(
    private val database: SurvayDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}