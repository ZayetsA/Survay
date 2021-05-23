package com.onix.internship.survay.ui.manager.tests

import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.launch

class TestViewModel(private val database: SurvayDatabase) : ViewModel(),
    RecycleViewCheckBoxListener {

    private var _testsList = MutableLiveData<List<Tests>>()
    val testsList: LiveData<List<Tests>>
        get() = getTestList()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private fun getTestList(): LiveData<List<Tests>> {
        viewModelScope.launch {
            _testsList.value = database.testsDao.getAllTests()
        }
        return _testsList
    }

    fun openAddNewTestFragment(){
        _navigationEvent.postValue(TestListFragmentDirections.actionTestListFragmentToCreateTestFragment())
    }


    override fun onAccessChange(view: View, test: Tests) {
        val checkBox = view as CheckBox
        if (checkBox.isChecked) {
            viewModelScope.launch {
            }
        } else {
            viewModelScope.launch {
            }
        }
    }
}