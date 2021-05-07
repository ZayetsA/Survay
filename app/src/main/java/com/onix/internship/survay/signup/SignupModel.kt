package com.onix.internship.survay.signup

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.onix.internship.survay.util.ErrorsCatcher
import kotlinx.android.parcel.Parcelize

@Parcelize
class SignupModel(
    private var _firstName: String = "",
    private var _secondName: String = "",
    private var _login: String = "",
    private var _password: String = "",
    private var _passwordConfirmation: String = ""
) : BaseObservable(), Parcelable {

    @get:Bindable
    var firstName: String = _firstName
        set(value) {
            _firstName = value
            field = value
            notifyPropertyChanged(BR.firstName)
        }

    @get:Bindable
    var secondName: String = _secondName
        set(value) {
            _secondName = value
            field = value
            notifyPropertyChanged(BR.secondName)
        }

    @get:Bindable
    var login: String = _login
        set(value) {
            _login = value
            field = value
            notifyPropertyChanged(BR.login)
        }

    @get:Bindable
    var password: String = _password
        set(value) {
            _password = value
            field = value
            notifyPropertyChanged(BR.password)
        }

    @get:Bindable
    var passwordConfirmation: String = _passwordConfirmation
        set(value) {
            _passwordConfirmation = value
            field = value
            notifyPropertyChanged(BR.passwordConfirmation)
        }

    fun isEmptyEditText(data: String): ErrorsCatcher {
        return if (data.isEmpty()) {
            ErrorsCatcher.EMPTY_FIELD
        } else ErrorsCatcher.NO
    }

    fun isEmpty(): Boolean {
        return _firstName.isEmpty() || _secondName.isEmpty() || _login.isEmpty() || _password.isEmpty() || passwordConfirmation.isEmpty()
    }
}