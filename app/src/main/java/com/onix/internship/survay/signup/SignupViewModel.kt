package com.onix.internship.survay.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.onix.internship.survay.events.SingleLiveEvent

class SignupViewModel : ViewModel() {

    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    fun showLoginFragment() {
        _navigationLiveEvent.value = SignupFragmentDirections.actionSignupFragmentToUserList2()
    }
}