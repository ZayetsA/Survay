package com.onix.internship.survay.ui.manager.tests.questions.new_question

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.answers.Answer
import com.onix.internship.survay.database.tables.questions.Question
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.util.ErrorsCatcher
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.launch

class CreateQuestionViewModel(private val database: SurvayDatabase, private val test: Tests) :
    ViewModel() {
    val model = CreateQuestionModel()
    lateinit var currentTests: List<Question>

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _errorQuestion = MutableLiveData(ErrorsCatcher.NO)

    val errorQuestion: LiveData<ErrorsCatcher>
        get() = _errorQuestion

    private val _errorVarFirst = MutableLiveData(ErrorsCatcher.NO)

    val errorVarFirst: LiveData<ErrorsCatcher>
        get() = _errorVarFirst

    private val _errorVarSecond = MutableLiveData(ErrorsCatcher.NO)

    val errorVarSecond: LiveData<ErrorsCatcher>
        get() = _errorVarSecond

    private val _errorVarThird = MutableLiveData(ErrorsCatcher.NO)

    val errorVarThird: LiveData<ErrorsCatcher>
        get() = _errorVarThird

    private val _errorVarFourth = MutableLiveData(ErrorsCatcher.NO)

    val errorVarFourth: LiveData<ErrorsCatcher>
        get() = _errorVarFourth

    private val _errorAnswer = MutableLiveData(ErrorsCatcher.NO)

    val errorAnswer: LiveData<ErrorsCatcher>
        get() = _errorAnswer

    fun addQuestionToDatabase() {
        model.apply {
            _errorQuestion.value = isEmptyEditText(testQuestion)
            _errorVarFirst.value = isEmptyEditText(testVarFirst)
            _errorVarSecond.value = isEmptyEditText(testVarSecond)
            _errorVarThird.value = isEmptyEditText(testVarThird)
            _errorVarFourth.value = isEmptyEditText(testVarFourth)
            _errorAnswer.value = isEmptyEditText(testAnswer)
            if (!isEmpty()) {
                viewModelScope.launch {
                    database.questionDao.insert(Question(0, test.testId, testQuestion))
                    currentTests = database.questionDao.getLatestQuestion()
                    if (currentTests.isNotEmpty()) {
                        database.answerDao.insert(
                            Answer(
                                0,
                                currentTests.first().questionId,
                                1,
                                testVarFirst,
                                testVarSecond,
                                testVarThird,
                                testVarFourth,
                                testAnswer
                            )
                        )
                        testQuestion = ""
                        testVarFirst = ""
                        testVarSecond = ""
                        testVarThird = ""
                        testVarFourth = ""
                        testAnswer = ""

                        _navigationEvent.postValue(
                            CreateQuestionFragmentDirections.actionCreateQuestionFragmentToQuestionListFragment2(
                                test
                            )
                        )
                    } else {
                        Log.d("BLIN!", "No such text!")
                    }
                }
            }
            Log.d("MODEL", model.toString())
        }
    }
}