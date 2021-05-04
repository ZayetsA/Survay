package com.onix.internship.survay.login

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.events.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    val users = repository.users

    @Bindable
    val inputUserName = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _emptyFieldsError = MutableLiveData<Boolean>()
    val emptyFieldsError: LiveData<Boolean>
        get() = _emptyFieldsError

    private val _errorToastUsername = MutableLiveData<Boolean>()
    val errorToastUsername: LiveData<Boolean>
        get() = _errorToastUsername

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()
    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword

    private val _acceptNavigation = MutableLiveData<Boolean>()
    val acceptNavigation: LiveData<Boolean>
        get() = _acceptNavigation


    fun showUserListFragment() {
        if (inputUserName.value == null || inputPassword.value == null) {
            _emptyFieldsError.value = true
        } else {
            uiScope.launch {
                val userName = repository.getUserName(inputUserName.value!!)
                if (userName != null) {
                    if (userName.password == inputPassword.value) {
                        inputUserName.value = null
                        inputPassword.value = null
                        _acceptNavigation.value = true
                    } else {
                        _errorToastInvalidPassword.value = true
                    }
                } else {
                    _errorToastUsername.value = true
                }
            }
        }
    }

    fun doneNavigationToUserDetails() {
        _acceptNavigation.value = false
    }

    fun checkedEmptyFields() {
        _emptyFieldsError.value = false
    }

    fun doneNotifyNickNameError() {
        _errorToastUsername.value = false
    }

    fun doneNotifyPasswordError() {
        _errorToastInvalidPassword.value = false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}