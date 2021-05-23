package com.onix.internship.survay.ui.manager.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onix.internship.survay.database.SurvayDatabase
import kotlinx.coroutines.launch

class ResultsViewModel(private val database: SurvayDatabase) : ViewModel() {

    val model = ResultsModel()

    private val _isDataLoading = MutableLiveData(true)
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    init {
        getStudentsResults()
    }

    private fun getStudentsResults() {
        viewModelScope.launch {
            model.apply {
                users = database.userDatabaseDao.getStudents()
                resultList = database.resultDao.getAllResults()
                tests = database.testsDao.getAllTests()
            }
            _isDataLoading.postValue(false)
        }
    }
}
