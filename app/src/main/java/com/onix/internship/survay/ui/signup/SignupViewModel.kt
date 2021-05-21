package com.onix.internship.survay.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.auth.Auth
import com.onix.internship.survay.database.tables.user.User
import com.onix.internship.survay.ui.tab.TabFragmentDirections
import com.onix.internship.survay.util.ErrorsCatcher
import com.onix.internship.survay.util.MD5
import com.onix.internship.survay.util.SingleLiveEvent
import com.onix.internship.survay.util.UserRoleStates
import kotlinx.coroutines.launch

class SignupViewModel(private val database: SurvayDatabase) : ViewModel() {

    val model = SignupModel()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val mD5 = MD5()

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
                viewModelScope.launch {
                    val username = database.userDatabaseDao.getLogin(login)
                    if (username.isNotEmpty()) {
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
                                val role: UserRoleStates =
                                    if (database.userDatabaseDao.getCount() == 0) {
                                        UserRoleStates.ADMIN
                                    } else {
                                        UserRoleStates.USER
                                    }

                                val user =
                                    User(0, firstName, secondName, login, hashedPassword, role)
                                val userId = database.userDatabaseDao.insert(user)
                                database.authDao.insert(
                                    Auth(
                                        authUserId = userId,
                                        timeStamp = System.currentTimeMillis()
                                    )
                                )
                                firstName = ""
                                secondName = ""
                                login = ""
                                password = ""
                                passwordConfirmation = ""
                                _navigationEvent.postValue(TabFragmentDirections.actionTabFragmentSelf())
                            }
                        }
                    }
                }
            }
        }
    }
}