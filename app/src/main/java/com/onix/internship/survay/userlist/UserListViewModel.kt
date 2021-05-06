package com.onix.internship.survay.userlist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onix.internship.survay.database.RegisterRepository

class UserListViewModel(repository: RegisterRepository, application: Application) : ViewModel() {
    val usersAndMentors = repository.usersAndMentors

    private val _acceptNavigation = MutableLiveData<Boolean>()
    val acceptNavigation: LiveData<Boolean>
        get() = _acceptNavigation

    fun doneNavigating() {
        _acceptNavigation.value = false
    }

}