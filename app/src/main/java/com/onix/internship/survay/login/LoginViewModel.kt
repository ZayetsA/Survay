package com.onix.internship.survay.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.tab.TabFragmentDirections
import com.onix.internship.survay.util.ErrorsCatcher
import com.onix.internship.survay.util.MD5
import com.onix.internship.survay.util.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: RegisterRepository) : ViewModel() {

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
                    val userLogin = repository.getUserName(login)
                    if (userLogin != null) {
                        if (userLogin.password == mD5.md5(password)) {
                            login = ""
                            password = ""
                            _navigationEvent.postValue(TabFragmentDirections.actionTabFragmentToUserList2())
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

}