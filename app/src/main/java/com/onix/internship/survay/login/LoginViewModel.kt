package com.onix.internship.survay.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.onix.internship.survay.events.SingleLiveEvent
import com.onix.internship.survay.tab.TabFragmentDirections

class LoginViewModel : ViewModel() {

    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    fun showUserListFragment() {
        _navigationLiveEvent.value = TabFragmentDirections.actionTabFragmentToUserList2()
    }
}