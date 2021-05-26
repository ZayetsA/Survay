package com.onix.internship.survay.ui.student.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.SurvayDatabase

class StudentViewModelFactory(
    private val database: SurvayDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            return StudentViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}