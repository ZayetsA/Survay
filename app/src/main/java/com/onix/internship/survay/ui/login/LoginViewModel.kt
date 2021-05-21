package com.onix.internship.survay.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.auth.Auth
import com.onix.internship.survay.ui.tab.TabFragmentDirections
import com.onix.internship.survay.util.ErrorsCatcher
import com.onix.internship.survay.util.MD5
import com.onix.internship.survay.util.SingleLiveEvent
import com.onix.internship.survay.util.UserRoleStates
import kotlinx.coroutines.launch

class LoginViewModel(private val database: SurvayDatabase) : ViewModel() {

    var model = LoginModel()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val mD5 = MD5()

    private val _errorLogin = MutableLiveData(ErrorsCatcher.NO)

    val errorLogin: LiveData<ErrorsCatcher>
        get() = _errorLogin

    private val _errorPassword = MutableLiveData(ErrorsCatcher.NO)

    val errorPassword: LiveData<ErrorsCatcher>
        get() = _errorPassword

    fun showUserListFragment() {
        model.apply {
            _errorLogin.value = isEmptyEditText(login)
            _errorPassword.value = isEmptyEditText(password)
            if (!isEmpty()) {
                viewModelScope.launch {
                    val userLogin = database.userDatabaseDao.getLogin(login)
                    if (userLogin.isNotEmpty()) {
                        if (userLogin.first().password == mD5.md5(password)) {
                            val userlist = database.userDatabaseDao.get(login, mD5.md5(password))
                            if (userlist.isNotEmpty()) {
                                database.authDao.insert(
                                    Auth(
                                        authUserId = userlist.first().userId.toLong(),
                                        timeStamp = System.currentTimeMillis()
                                    )
                                )
                                login = ""
                                password = ""
                                navigateToRightFragment(userLogin.first().role)
                            } else {
                                _errorLogin.value = ErrorsCatcher.INCORRECT_LOGIN
                            }
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

    private fun navigateToRightFragment(role: UserRoleStates) {
        when (role) {
            UserRoleStates.ADMIN -> {
                _navigationEvent.postValue(TabFragmentDirections.actionTabFragmentToAdmin())
            }
            UserRoleStates.MANAGER -> {
                _navigationEvent.postValue(TabFragmentDirections.actionTabFragmentToManagerFragment())
            }
            UserRoleStates.USER -> _navigationEvent.postValue(TabFragmentDirections.actionTabFragmentToStudentFragment())
            else -> _navigationEvent.postValue(TabFragmentDirections.actionTabFragmentSelf())
        }
    }
}