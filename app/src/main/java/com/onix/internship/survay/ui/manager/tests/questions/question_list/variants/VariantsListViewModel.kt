package com.onix.internship.survay.ui.manager.tests.questions.question_list.variants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.answers.Answer
import com.onix.internship.survay.database.tables.questions.Question
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.launch

class VariantsListViewModel(
    private val database: SurvayDatabase,
    private val question: Question,
    private val tests: Tests
) :
    ViewModel() {

    private var _answerList = MutableLiveData<List<Answer>>()
    val answerList: LiveData<List<Answer>>
        get() = getAnswersList()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private fun getAnswersList(): LiveData<List<Answer>> {
        viewModelScope.launch {
            _answerList.value = database.answerDao.getAnswersByQuestion(question.questionId)
        }
        return _answerList
    }


    fun addVariant() {
        _navigationEvent.postValue(
            VariantsListFragmentDirections.actionVariantsListFragment2ToCreateAnswerFragment(
                question, tests
            )
        )
    }
}