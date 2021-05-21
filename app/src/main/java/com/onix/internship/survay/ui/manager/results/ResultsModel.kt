package com.onix.internship.survay.ui.manager.results

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.onix.internship.survay.database.tables.results.Result
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.database.tables.user.User

class ResultsModel(
    _users: List<User> = emptyList(),
    _tests: List<Tests> = emptyList(),
    _resultList: List<Result> = emptyList()
) : BaseObservable() {
    @Bindable
    var users = _users
        set(value) {
            field = value
            notifyPropertyChanged(BR.users)
        }

    @Bindable
    var tests = _tests
        set(value) {
            field = value
            notifyPropertyChanged(BR.tests)
        }


    @Bindable
    var resultList = _resultList
        set(value) {
            field = value
            notifyPropertyChanged(BR.resultList)
        }

}