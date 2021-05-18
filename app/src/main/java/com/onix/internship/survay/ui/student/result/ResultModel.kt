package com.onix.internship.survay.ui.student.result

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.onix.internship.survay.database.tables.user.User

class ResultModel(

    _result: String = "",
    _currentUser: User = User()

) : BaseObservable() {

    @Bindable
    var currentUser = _currentUser
        set(value) {
            field = value
            notifyPropertyChanged(BR.currentUser)
        }

    @Bindable
    var userResult = _result
        set(value) {
            field = value
            notifyPropertyChanged(BR.userResult)
        }
}