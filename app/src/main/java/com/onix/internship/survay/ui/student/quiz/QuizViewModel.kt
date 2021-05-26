package com.onix.internship.survay.ui.student.quiz

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.answers.Answer
import com.onix.internship.survay.database.tables.results.Result
import com.onix.internship.survay.ui.student.quiz.adapter.OnItemClick
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizViewModel(private val database: SurvayDatabase, testId: Int) : ViewModel(), OnItemClick {

    var model = QuizModel()
    private var curPosition = 1
    private var score = 0
    private var curScore = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            model.apply {
                currentUser = database.authDao.getCurrentUser().first().user
                questionAndAnswerList = database.questionAndAnswerDao.getQuestionsAndAnswers(testId)
            }
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

    private val _testProgressBarState = MutableLiveData<Int>()
    val testProgressBarState: LiveData<Int>
        get() = _testProgressBarState

    private val _testProgressBarMaxValue = MutableLiveData<Int>()
    val testProgressBarMaxValue: LiveData<Int>
        get() = _testProgressBarMaxValue

    private val _testProgressBarText = MutableLiveData<String>()
    val testProgressBarText: LiveData<String>
        get() = _testProgressBarText

    private val _answerVariants = MutableLiveData<List<Answer>>()
    val answerVariants: LiveData<List<Answer>>
        get() = _answerVariants

    private val _isItemClicked = MutableLiveData(false)
    val isItemClicked: LiveData<Boolean>
        get() = _isItemClicked

    private fun setupQuestions() {
        if (model.questionAndAnswerList.isNotEmpty()) {
            val questionListSize = model.questionAndAnswerList.size
            _testQuestion.postValue(model.questionAndAnswerList[curPosition - 1].question.text)
            _answerVariants.postValue(model.questionAndAnswerList[curPosition - 1].listOfVariants)
            _testProgressBarMaxValue.postValue(model.questionAndAnswerList.size)
            _testProgressBarState.postValue(curPosition)
            _testProgressBarText.postValue("$curPosition / $questionListSize")
        } else {
            Log.d("TEST_ID", "NO TESTS!!")
        }
    }

    override fun onItemClick(view: View, answer: Answer) {
        _isItemClicked.value = true
        curScore = answer.score
    }

    fun onSubmitClick() {
        score += curScore
        if (curPosition < model.questionAndAnswerList.size) {
            curPosition++
        } else {
            val userResult = Result(
                0,
                model.currentUser.userId,
                System.currentTimeMillis(),
                model.questionAndAnswerList.first().question.testId,
                score
            )
            Log.d("RESULT", userResult.toString())
            viewModelScope.launch {
                database.resultDao.insert(userResult)
            }
            _navigationEvent.postValue(
                QuizFragmentDirections.actionQuizFragmentToResultFragment(
                )
            )
        }
        _isItemClicked.value = false
        setupQuestions()
    }
}

