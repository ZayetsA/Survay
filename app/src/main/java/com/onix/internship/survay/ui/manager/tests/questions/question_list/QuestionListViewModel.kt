package com.onix.internship.survay.ui.manager.tests.questions.question_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.questions.Question
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionListViewModel(private val currentTests: Tests, private val database: SurvayDatabase) :
    ViewModel() {

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private var _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>>
        get() = getTests()

    private fun getTests(): LiveData<List<Question>> {
        viewModelScope.launch(Dispatchers.IO) {
            _questions.postValue(database.questionDao.getQuestionsByTest(currentTests.testId))

        }
        return _questions
    }

    fun addQuestion() {
        _navigationEvent.postValue(
            QuestionListFragmentDirections.actionQuestionListFragment2ToCreateQuestionFragment(
                currentTests
            )
        )
    }
}