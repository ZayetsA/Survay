package com.onix.internship.survay.login

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.onix.internship.survay.util.ErrorsCatcher
import kotlinx.android.parcel.Parcelize

@Parcelize
class LoginModel(
    private var _login: String = "",
    private var _password: String = ""
) : BaseObservable(), Parcelable {

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

    fun isEmptyEditText(data: String): ErrorsCatcher {
        return if (data.isEmpty()) {
            ErrorsCatcher.EMPTY_FIELD
        } else ErrorsCatcher.NO
    }

    fun isEmpty(): Boolean {
        return _login.isEmpty() || _password.isEmpty()
    }
}