package com.onix.internship.survay.ui.manager.tests.questions.question_list.variants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.questions.Question
import com.onix.internship.survay.database.tables.tests.Tests

@Suppress("UNCHECKED_CAST")
class VariantsListViewModelFactory(
    private val database: SurvayDatabase,
    private val question: Question,
    private val tests: Tests
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VariantsListViewModel::class.java)) {
            return VariantsListViewModel(database, question, tests) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}