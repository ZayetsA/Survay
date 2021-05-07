package com.onix.internship.survay.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.database.User
import com.onix.internship.survay.util.ErrorsCatcher
import com.onix.internship.survay.util.MD5
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignupViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application) {

    val model = SignupModel()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val mD5 = MD5()

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    private val _errorFirstName = MutableLiveData(ErrorsCatcher.NO)
    val errorFirstName: LiveData<ErrorsCatcher>
        get() = _errorFirstName

    private val _errorSecondName = MutableLiveData(ErrorsCatcher.NO)
    val errorSecondName: LiveData<ErrorsCatcher>
        get() = _errorSecondName

    private val _errorLogin = MutableLiveData(ErrorsCatcher.NO)
    val errorLogin: LiveData<ErrorsCatcher>
        get() = _errorLogin

    private val _errorPassword = MutableLiveData(ErrorsCatcher.NO)
    val errorPassword: LiveData<ErrorsCatcher>
        get() = _errorPassword

    private val _errorPasswordConfirmation = MutableLiveData(ErrorsCatcher.NO)
    val errorPasswordConfirmation: LiveData<ErrorsCatcher>
        get() = _errorPasswordConfirmation

    fun showLoginFragment() {
        model.apply {
            _errorFirstName.value = isEmptyEditText(firstName)
            _errorSecondName.value = isEmptyEditText(secondName)
            _errorLogin.value = isEmptyEditText(login)
            _errorPassword.value = isEmptyEditText(password)
            _errorPasswordConfirmation.value = isEmptyEditText(passwordConfirmation)
            if (!isEmpty()) {
                uiScope.launch {
                    val username = repository.getUserName(login)
                    if (username != null) {
                        _errorLogin.value = ErrorsCatcher.EXISTING_LOGIN
                    } else {
                        if (password != passwordConfirmation) {
                            _errorPassword.value = ErrorsCatcher.PASSWORD_MATCH_ERROR
                            _errorPasswordConfirmation.value = ErrorsCatcher.PASSWORD_MATCH_ERROR
                        } else {
                            if (password.length < 6) {
                                _errorPassword.value = ErrorsCatcher.EASY_PASSWORD
                                _errorPasswordConfirmation.value = ErrorsCatcher.EASY_PASSWORD
                            } else {
                                val hashedPassword = mD5.md5(password)
                                val role: Int = if (repository.getNumUsers() == 0) {
                                    0
                                } else {
                                    1
                                }
                                insert(User(0, firstName, secondName, login, hashedPassword, role))
                                firstName = ""
                                secondName = ""
                                login = ""
                                password = ""
                                passwordConfirmation = ""
                                _navigate.value = true
                            }
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
}