package com.onix.internship.survay.ui.manager.tests.new_test

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.util.ErrorsCatcher
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.launch

class CreateTestViewModel(private val database: SurvayDatabase) : ViewModel() {

    var model = CreateTestModel()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _errorTestName = MutableLiveData(ErrorsCatcher.NO)

    val errorTestName: LiveData<ErrorsCatcher>
        get() = _errorTestName

    private val _errorTestDescription = MutableLiveData(ErrorsCatcher.NO)

    val errorTestDescription: LiveData<ErrorsCatcher>
        get() = _errorTestDescription

    fun addTestToDatabase() {
        model.apply {
            Log.d("NAME", testName)
            Log.d("DESC", testDescription)
            _errorTestName.value = isEmptyEditText(testName)
            _errorTestDescription.value = isEmptyEditText(testDescription)
            if (!isEmpty()) {
                viewModelScope.launch {
                    database.testsDao.insert(Tests(0, testName, testDescription))
                    testName = ""
                    testDescription = ""
                    _navigationEvent.postValue(CreateTestFragmentDirections.actionCreateTestFragmentToTestListFragment())
                }
            }
        }
    }
}