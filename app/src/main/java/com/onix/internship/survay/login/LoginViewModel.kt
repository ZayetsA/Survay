package com.onix.internship.survay.login

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.util.MD5
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

    private val mD5 = MD5()

    @Bindable
    val inputUserName = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _errorEmptyLogin = MutableLiveData<Boolean>()

    val errorEmptyLogin: LiveData<Boolean>
        get() = _errorEmptyLogin

    private val _errorEmptyPassword = MutableLiveData<Boolean>()

    val errorEmptyPassword: LiveData<Boolean>
        get() = _errorEmptyPassword

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
            if (inputUserName.value == null) _errorEmptyLogin.value = true
            if (inputPassword.value == null) _errorEmptyPassword.value = true
        } else {
            uiScope.launch {
                val userName = repository.getUserName(inputUserName.value!!)
                if (userName != null) {
                    if (userName.password == mD5.md5(inputPassword.value!!)) {
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