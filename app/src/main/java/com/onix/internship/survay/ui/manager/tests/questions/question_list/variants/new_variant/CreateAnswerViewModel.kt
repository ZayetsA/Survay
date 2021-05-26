package com.onix.internship.survay.ui.manager.tests.questions.question_list.variants.new_variant

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

class CreateAnswerViewModel(
    private val database: SurvayDatabase,
    private val question: Question,
    private val tests: Tests
) :
    ViewModel() {
    val model = CreateAnswerModel()
    lateinit var currentQuestion: List<Question>

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _errorAnswer = MutableLiveData(ErrorsCatcher.NO)

    val errorAnswer: LiveData<ErrorsCatcher>
        get() = _errorAnswer

    private val _errorScore = MutableLiveData(ErrorsCatcher.NO)

    val errorScore: LiveData<ErrorsCatcher>
        get() = _errorScore

    fun addAnswerToDatabase() {
        model.apply {
            _errorAnswer.value = isEmptyEditText(questionAnswerVar)
            _errorScore.value = isEmptyEditText(questionAnswerScore)
            if (!isEmpty()) {
                viewModelScope.launch {
                    database.answerDao.insert(
                        Answer(
                            0,
                            question.questionId,
                            questionAnswerScore.toInt(),
                            questionAnswerVar
                        )
                    )
                    questionAnswerVar = ""
                    questionAnswerScore = ""
                    _navigationEvent.postValue(
                        CreateAnswerFragmentDirections.actionCreateAnswerFragmentToVariantsListFragment2(
                            question, tests
                        )
                    )
                }
            }
        }
    }
}