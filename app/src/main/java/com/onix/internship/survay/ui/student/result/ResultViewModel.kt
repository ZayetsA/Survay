package com.onix.internship.survay.ui.student.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultViewModel(private val database: SurvayDatabase) : ViewModel() {

    val model = ResultModel()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _isDataLoading = MutableLiveData(true)
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    init {
        viewModelScope.launch(Dispatchers.IO) {
            model.currentUser = database.authDao.getCurrentUser().user
            val result = database.resultDao.getLatestResult()?.score
            model.userResult = "You score is: $result"
            _isDataLoading.postValue(false)
        }
    }

    fun goToTestListPage() {
        _navigationEvent.postValue(ResultFragmentDirections.actionResultFragmentToStudentFragment())
    }
}