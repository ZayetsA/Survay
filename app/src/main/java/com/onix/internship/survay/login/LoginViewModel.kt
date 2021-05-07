package com.onix.internship.survay.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.util.ErrorsCatcher
import com.onix.internship.survay.util.MD5
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application) {

    var model = LoginModel()

    private val mD5 = MD5()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _errorLogin = MutableLiveData(ErrorsCatcher.NO)

    val errorLogin: LiveData<ErrorsCatcher>
        get() = _errorLogin

    private val _errorPassword = MutableLiveData(ErrorsCatcher.NO)

    val errorPassword: LiveData<ErrorsCatcher>
        get() = _errorPassword

    private val _acceptNavigation = MutableLiveData<Boolean>()
    val acceptNavigation: LiveData<Boolean>
        get() = _acceptNavigation

    fun showUserListFragment() {
        model.apply {
            _errorLogin.value = isEmptyEditText(login)
            _errorPassword.value = isEmptyEditText(password)
            if (!isEmpty()) {
                uiScope.launch {
                    val userLogin = repository.getUserName(login)
                    if (userLogin != null) {
                        if (userLogin.password == mD5.md5(password)) {
                            login = ""
                            password = ""
                            _acceptNavigation.value = true
                        } else {
                            _errorPassword.value = ErrorsCatcher.INCORRECT_PASSWORD
                        }
                    } else {
                        _errorLogin.value = ErrorsCatcher.INCORRECT_LOGIN
                    }
                }
            }
        }
    }

    fun doneNavigationToUserDetails() {
        _acceptNavigation.value = false
    }
}