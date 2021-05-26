package com.onix.internship.survay.ui.manager.tests.questions.new_question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.questions.Question
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.util.ErrorsCatcher
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.launch

class CreateQuestionViewModel(private val database: SurvayDatabase, private val tests: Tests) :
    ViewModel() {
    val model = CreateQuestionModel()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _errorQuestion = MutableLiveData(ErrorsCatcher.NO)

    val errorQuestion: LiveData<ErrorsCatcher>
        get() = _errorQuestion

    fun addQuestionToDatabase() {
        model.apply {
            _errorQuestion.value = isEmptyEditText(testQuestion)
            if (!isEmpty()) {
                viewModelScope.launch {
                    database.questionDao.insert(Question(0, tests.testId, testQuestion))
                    testQuestion = ""
                    _navigationEvent.postValue(
                        CreateQuestionFragmentDirections.actionCreateQuestionFragmentToQuestionListFragment2(
                            tests
                        )
                    )
                }
            }
        }
    }
}