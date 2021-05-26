package com.onix.internship.survay.ui.manager.tests.questions.new_question

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.onix.internship.survay.util.ErrorsCatcher
import kotlinx.android.parcel.Parcelize

@Parcelize
class CreateQuestionModel(
    private var _testQuestion: String = "",
) : BaseObservable(), Parcelable {

    @get:Bindable
    var testQuestion: String = _testQuestion
        set(value) {
            _testQuestion = value
            field = value
            notifyPropertyChanged(BR.testQuestion)
        }

    fun isEmptyEditText(data: String): ErrorsCatcher {
        return if (data.isEmpty()) {
            ErrorsCatcher.EMPTY_FIELD
        } else ErrorsCatcher.NO
    }

    fun isEmpty(): Boolean {
        return _testQuestion.isEmpty()
    }
}