package com.onix.internship.survay.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.database.User
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: RegisterRepository) : ViewModel() {

    private var _usersAndMentors = MutableLiveData<List<User>>()
    val usersAndMentors: LiveData<List<User>>
        get() = getUsersAndMentors()

    private val _acceptNavigation = MutableLiveData<Boolean>()
    val acceptNavigation: LiveData<Boolean>
        get() = _acceptNavigation

    fun doneNavigating() {
        _acceptNavigation.value = false
    }

    private fun getUsersAndMentors(): MutableLiveData<List<User>> {
        viewModelScope.launch {
            _usersAndMentors.value = repository.getUsersAndMentors()
        }
        return _usersAndMentors
    }
}