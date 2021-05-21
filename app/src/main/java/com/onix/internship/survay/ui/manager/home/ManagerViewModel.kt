package com.onix.internship.survay.ui.manager.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.onix.internship.survay.util.SingleLiveEvent

class ManagerViewModel : ViewModel() {

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    fun goToTestList() {
        _navigationEvent.postValue(
            ManagerFragmentDirections.actionManagerFragmentToTestListFragment()
        )
    }

    fun goToStudentResults() {
        _navigationEvent.postValue(
            ManagerFragmentDirections.actionManagerFragmentToResultsFragment()
        )
    }
}