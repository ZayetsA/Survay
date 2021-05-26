package com.onix.internship.survay.ui.student.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.SurvayDatabase

class QuizViewModelFactory(
    private val database: SurvayDatabase,
    private val testId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(database, testId) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
