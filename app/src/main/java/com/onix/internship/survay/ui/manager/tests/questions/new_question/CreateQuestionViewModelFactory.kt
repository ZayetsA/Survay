package com.onix.internship.survay.ui.manager.tests.questions.new_question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.tests.Tests

@Suppress("UNCHECKED_CAST")
class CreateQuestionViewModelFactory(
    private val database: SurvayDatabase,
    private val tests: Tests,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateQuestionViewModel::class.java)) {
            return CreateQuestionViewModel(database, tests) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}