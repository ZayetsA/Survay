package com.onix.internship.survay.ui.student.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.ui.student.list.adapter.TestItemOnClickListener
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.launch

class StudentViewModel(private val database: SurvayDatabase) : ViewModel(),
    TestItemOnClickListener {
    private var _test = MutableLiveData<List<Tests>>()
    val tests: LiveData<List<Tests>>
        get() = getTestList()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private fun getTestList(): MutableLiveData<List<Tests>> {
        viewModelScope.launch {
            _test.value = database.testsDao.getAllTests()
        }
        return _test
    }

    override fun onItemClick(tests: Tests) {
        _navigationEvent.postValue(
            StudentFragmentDirections.actionStudentFragmentToQuizFragment(tests.testId)
        )
    }
}