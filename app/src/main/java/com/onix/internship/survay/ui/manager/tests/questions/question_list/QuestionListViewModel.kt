package com.onix.internship.survay.ui.manager.tests.questions.question_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.questions.QuestionAndAnswer
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionListViewModel(private val currentTest: Tests, private val database: SurvayDatabase) :
    ViewModel() {

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private var _questions = MutableLiveData<List<QuestionAndAnswer>>()
    val questions: LiveData<List<QuestionAndAnswer>>
        get() = getTests()

    private fun getTests(): LiveData<List<QuestionAndAnswer>> {
        viewModelScope.launch(Dispatchers.IO) {
            _questions.postValue(database.questionAndAnswerDao.getQuestionsAndAnswers(currentTest.testId))

        }
        return _questions
    }

    fun addQuestion() {
        _navigationEvent.postValue(
            QuestionListFragmentDirections.actionQuestionListFragment2ToCreateQuestionFragment(
                currentTest
            )
        )
    }
}