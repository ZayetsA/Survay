package com.onix.internship.survay.ui.manager.tests.questions.question_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.tests.Tests

@Suppress("UNCHECKED_CAST")
class QuestionListViewModelFactory(
    private val currentTest: Tests,
    private val database: SurvayDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionListViewModel::class.java)) {
            return QuestionListViewModel(currentTest, database) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}