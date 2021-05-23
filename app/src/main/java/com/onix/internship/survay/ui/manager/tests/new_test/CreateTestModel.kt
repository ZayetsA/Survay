package com.onix.internship.survay.ui.manager.tests.new_test

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.onix.internship.survay.util.ErrorsCatcher
import kotlinx.android.parcel.Parcelize

@Parcelize
class CreateTestModel(
    private var _testName: String = "",
    private var _testDescription: String = "",

    ) : BaseObservable(), Parcelable {

    @get:Bindable
    var testName: String = _testName
        set(value) {
            _testName = value
            field = value
            notifyPropertyChanged(BR.testName)
        }

    @get:Bindable
    var testDescription: String = _testDescription
        set(value) {
            _testDescription = value
            field = value
            notifyPropertyChanged(BR.testDescription)
        }

    fun isEmptyEditText(data: String): ErrorsCatcher {
        return if (data.isEmpty()) {
            ErrorsCatcher.EMPTY_FIELD
        } else ErrorsCatcher.NO
    }

    fun isEmpty(): Boolean {
        return _testName.isEmpty() || _testDescription.isEmpty()
    }
}