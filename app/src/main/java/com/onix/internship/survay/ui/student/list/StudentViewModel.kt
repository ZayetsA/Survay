package com.onix.internship.survay.ui.student.list

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

class StudentViewModel(private val database: SurvayDatabase) : ViewModel(),
    TestItemOnClickListener {
    private var _test = MutableLiveData<List<Tests>>()
    val test: LiveData<List<Tests>>
        get() = getTestList()

//    init {
//        insertTest1()
//        insertQuestion1()
//        insertAnswers1()
//////        insertTest2()
//////        insertQuestion2()
////        insertAnswers2()
//    }

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private fun getTestList(): MutableLiveData<List<Tests>> {
        viewModelScope.launch {
            _test.value = database.testsDao.getAllTests()
        }
        return _test
    }

    override fun onItemClick(test: Tests) {
        _navigationEvent.postValue(
            StudentFragmentDirections.actionStudentFragmentToQuizFragment(test.testId)
        )
    }


    private fun insertTest1() {
        viewModelScope.launch {
            database.testsDao.insert(Tests(0, "math", "some math questions"))
        }
    }


    private fun insertQuestion1() {
        viewModelScope.launch {
            database.questionDao.insert(Question(0, 0, "What is love?"))
        }
    }

    private fun insertAnswers1() {
        viewModelScope.launch {
            database.answerDao.insert(Answer(0, 0, 1, "love", "var2", "var3", "var4", "love"))
        }
    }


    private fun insertTest2() {
        viewModelScope.launch {
            database.testsDao.insert(Tests(0, "proggramming", "some proggramming questions"))
        }
    }


    private fun insertQuestion2() {
        viewModelScope.launch {
            database.questionDao.insert(Question(0, 1, "What is sex?"))
        }
    }

    private fun insertAnswers2() {
        viewModelScope.launch {
            database.answerDao.insert(Answer(0, 0, 1, "sexx", "sex", "xex", "dgdg", "sex"))
        }
    }
}