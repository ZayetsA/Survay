package com.onix.internship.survay.signup

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.database.User
import com.onix.internship.survay.events.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignupViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

    private val _navigationLiveEvent = SingleLiveEvent<NavDirections>()
    val navigationLiveEvent: LiveData<NavDirections> = _navigationLiveEvent

    private val userData: String? = null
    val userDetails = MutableLiveData<Array<User>>()

    @Bindable
    val inputFirstName = MutableLiveData<String>()

    @Bindable
    val inputLastName = MutableLiveData<String>()

    @Bindable
    val inputUserLogin = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    @Bindable
    val inputPasswordConfirmation = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errorToastUsername: LiveData<Boolean>
        get() = _errorToastUsername

    private val _errorToastPassword = MutableLiveData<Boolean>()

    val errorToastPassword: LiveData<Boolean>
        get() = _errorToastPassword

    fun showLoginFragment() {
        if (checkError()) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val userName = inputUserLogin.value?.let { repository.getUserName(it) }
                if (userName != null) {
                    _errorToastUsername.value = true
                } else {
                    if (inputPassword.value != inputPasswordConfirmation.value) {
                        _errorToastPassword.value = true
                    } else {
                        val firstName = inputFirstName.value!!
                        val lastName = inputLastName.value!!
                        val userLogin = inputUserLogin.value!!
                        val password = inputPassword.value!!
                        val role = 0
                        insert(User(0, firstName, lastName, userLogin, password, role))
                        inputFirstName.value = null
                        inputLastName.value = null
                        inputUserLogin.value = null
                        inputPassword.value = null
                        inputPasswordConfirmation.value = null
                        _navigate.value = true
                    }
                }
            }
        }
    }

    private fun insert(user: User): Job = viewModelScope.launch {
        repository.insert(user)
    }

    fun doneNavigating() {
        _navigate.value = false
    }

    fun doneNotificationUserName() {
        _errorToastUsername.value = false
    }

    fun doneToast() {
        _errorToast.value = false
    }

    fun donePasswordNotification() {
        _errorToastPassword.value = false
    }

    private fun checkError(): Boolean {
        return inputFirstName.value == null || inputLastName.value == null || inputUserLogin.value == null || inputPassword.value == null || inputPasswordConfirmation.value == null
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}