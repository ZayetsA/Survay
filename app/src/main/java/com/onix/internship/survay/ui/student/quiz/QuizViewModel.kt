package com.onix.internship.survay.ui.student.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.results.Result
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizViewModel(private val database: SurvayDatabase, testId: Int) : ViewModel() {

    var model = QuizModel()
    private var curPosition = 1
    private var score = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            model.currentUser = database.authDao.getCurrentUser().user
            Log.d("TEST_ID", testId.toString())
            model.questionList =
                database.questionAndAnswerDao.getQuestionsAndAnswers(0) // get from safeargs, but no tests, so 0
            setupQuestions()
            _isDataLoading.postValue(false)
        }
    }

    private val _isDataLoading = MutableLiveData(true)
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private var _testQuestion = MutableLiveData<String>()
    val testQuestion: LiveData<String>
        get() = _testQuestion

    private val _testOptionFirst = MutableLiveData<String>()
    val testOptionFirst: LiveData<String>
        get() = _testOptionFirst

    private val _testOptionSecond = MutableLiveData<String>()
    val testOptionSecond: LiveData<String>
        get() = _testOptionSecond

    private val _testOptionThird = MutableLiveData<String>()
    val testOptionThird: LiveData<String>
        get() = _testOptionThird

    private val _testOptionFourth = MutableLiveData<String>()
    val testOptionFourth: LiveData<String>
        get() = _testOptionFourth

    private val _testProgressBarState = MutableLiveData<Int>()
    val testProgressBarState: LiveData<Int>
        get() = _testProgressBarState

    private val _testProgressBarMaxValue = MutableLiveData<Int>()
    val testProgressBarMaxValue: LiveData<Int>
        get() = _testProgressBarMaxValue

    private val _testProgressBarText = MutableLiveData<String>()
    val testProgressBarText: LiveData<String>
        get() = _testProgressBarText

    private val _isItem1Clicked = MutableLiveData<Boolean>()
    val isItem1Clicked: LiveData<Boolean>
        get() = _isItem1Clicked

    private val _isItem2Clicked = MutableLiveData<Boolean>()
    val isItem2Clicked: LiveData<Boolean>
        get() = _isItem2Clicked

    private val _isItem3Clicked = MutableLiveData<Boolean>()
    val isItem3Clicked: LiveData<Boolean>
        get() = _isItem3Clicked

    private val _isItem4Clicked = MutableLiveData<Boolean>()
    val isItem4Clicked: LiveData<Boolean>
        get() = _isItem4Clicked

    private val _userResult = MutableLiveData<String>()
    val userResult: LiveData<String>
        get() = _userResult


    private fun setupQuestions() {
        if (model.questionList.isNotEmpty()) {
            val questionListSize = model.questionList.size
            model.question = model.questionList[curPosition - 1]
            _testQuestion.postValue(model.question.question.text)
            _testOptionFirst.postValue(model.question.answer.var1)
            _testOptionSecond.postValue(model.question.answer.var2)
            _testOptionThird.postValue(model.question.answer.var3)
            _testOptionFourth.postValue(model.question.answer.var4)
            _testProgressBarMaxValue.postValue(model.questionList.size)
            _testProgressBarState.postValue(curPosition)
            _testProgressBarText.postValue("$curPosition / $questionListSize")
        } else {
            Log.d("TEST_ID", "NO TESTS!!")
        }
    }

    fun onItem1Click() {
        reNullItems()
        _isItem1Clicked.value = true
        _userResult.value = _testOptionFirst.value
        _userResult.value?.let { Log.d("Answer", it) }
    }

    fun onItem2Click() {
        reNullItems()
        _isItem2Clicked.value = true
        _userResult.value = _testOptionSecond.value
        _userResult.value?.let { Log.d("Answer", it) }
    }

    fun onItem3Click() {
        reNullItems()
        _isItem3Clicked.value = true
        _userResult.value = _testOptionThird.value
        _userResult.value?.let { Log.d("Answer", it) }
    }

    fun onItem4Click() {
        reNullItems()
        _isItem4Clicked.value = true
        _userResult.value = testOptionFourth.value
        _userResult.value?.let { Log.d("Answer", it) }
    }

    private fun reNullItems() {
        _isItem1Clicked.value = false
        _isItem2Clicked.value = false
        _isItem3Clicked.value = false
        _isItem4Clicked.value = false
        _userResult.value = ""
    }

    fun onSubmitClick() {
        if (_userResult.value == model.question.answer.answer) {
            score++
        }
        if (curPosition < model.questionList.size) {
            curPosition++
        } else {
            val result = Result(
                0,
                model.currentUser.userId,
                System.currentTimeMillis(),
                model.questionList.first().question.testId,
                score
            )
            Log.d("RESULT", result.toString())
            viewModelScope.launch {
                database.resultDao.insert(result)
            }
            _navigationEvent.postValue(
                QuizFragmentDirections.actionQuizFragmentToResultFragment(
                )
            )
        }
        setupQuestions()
        reNullItems()
    }
}