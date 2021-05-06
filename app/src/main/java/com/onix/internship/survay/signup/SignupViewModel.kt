package com.onix.internship.survay.signup

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.database.User
import com.onix.internship.survay.util.MD5
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignupViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

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
    private val mD5 = MD5()

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    private val _errorEmptyFirstName = MutableLiveData<Boolean>()

    val errorEmptyFirstName: LiveData<Boolean>
        get() = _errorEmptyFirstName

    private val _errorEmptySecondName = MutableLiveData<Boolean>()

    val errorEmptySecondName: LiveData<Boolean>
        get() = _errorEmptySecondName

    private val _errorEmptyLogin = MutableLiveData<Boolean>()

    val errorEmptyLogin: LiveData<Boolean>
        get() = _errorEmptyLogin

    private val _errorEmptyPassword = MutableLiveData<Boolean>()

    val errorEmptyPassword: LiveData<Boolean>
        get() = _errorEmptyPassword

    private val _errorEmptyPasswordConfirmation = MutableLiveData<Boolean>()

    val errorEmptyPasswordConfirmation: LiveData<Boolean>
        get() = _errorEmptyPasswordConfirmation

    private val _errorExistingUsername = MutableLiveData<Boolean>()

    val errorExistingUsername: LiveData<Boolean>
        get() = _errorExistingUsername

    private val _errorPasswordMatch = MutableLiveData<Boolean>()

    val errorPasswordMatch: LiveData<Boolean>
        get() = _errorPasswordMatch

    private val _errorPasswordDifficult = MutableLiveData<Boolean>()
    val errorPasswordDifficult: LiveData<Boolean>
        get() = _errorPasswordDifficult

    fun showLoginFragment() {
        if (checkError()) {
            if (inputFirstName.value == null) _errorEmptyFirstName.value = true
            if (inputLastName.value == null) _errorEmptySecondName.value = true
            if (inputUserLogin.value == null) _errorEmptyLogin.value = true
            if (inputPassword.value == null) _errorEmptyPassword.value = true
            if (inputPasswordConfirmation.value == null) _errorEmptyPasswordConfirmation.value =
                true
        } else {
            uiScope.launch {
                val userName = inputUserLogin.value?.let { repository.getUserName(it) }
                if (userName != null) {
                    _errorExistingUsername.value = true
                } else {
                    if (inputPassword.value != inputPasswordConfirmation.value) {
                        _errorPasswordMatch.value = true
                    } else {
                        if (inputPassword.value!!.length < 6) {
                            _errorPasswordDifficult.value = true
                        } else {
                            val firstName = inputFirstName.value!!
                            val lastName = inputLastName.value!!
                            val userLogin = inputUserLogin.value!!
                            val password = mD5.md5(inputPassword.value!!)
                            val role: Int = if (repository.getNumUsers() == 0) {
                                0
                            } else {
                                1
                            }
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
    }

    private fun insert(user: User): Job = viewModelScope.launch {
        repository.insert(user)
    }

    fun doneNavigating() {
        _navigate.value = false
    }

    fun doneNotificationUserName() {
        _errorExistingUsername.value = false
    }

    fun donePasswordNotification() {
        _errorPasswordMatch.value = false
    }

    fun donePasswordDifficultNotification() {
        _errorPasswordDifficult.value = false
    }

    private fun checkError(): Boolean {
        return inputFirstName.value == null || inputLastName.value == null || inputUserLogin.value == null || inputPassword.value == null || inputPasswordConfirmation.value == null
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}