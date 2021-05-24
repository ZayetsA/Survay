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
    private var _testVarFirst: String = "",
    private var _testVarSecond: String = "",
    private var _testVarThird: String = "",
    private var _testVarFourth: String = "",
    private var _testAnswer: String = "",
) : BaseObservable(), Parcelable {

    @get:Bindable
    var testQuestion: String = _testQuestion
        set(value) {
            _testQuestion = value
            field = value
            notifyPropertyChanged(BR.testQuestion)
        }

    @get:Bindable
    var testVarFirst: String = _testVarFirst
        set(value) {
            _testVarFirst = value
            field = value
            notifyPropertyChanged(BR.testVarFirst)
        }

    @get:Bindable
    var testVarSecond: String = _testVarSecond
        set(value) {
            _testVarSecond = value
            field = value
            notifyPropertyChanged(BR.testVarSecond)
        }

    @get:Bindable
    var testVarThird: String = _testVarThird
        set(value) {
            _testVarThird = value
            field = value
            notifyPropertyChanged(BR.testVarThird)
        }

    @get:Bindable
    var testVarFourth: String = _testVarFourth
        set(value) {
            _testVarFourth = value
            field = value
            notifyPropertyChanged(BR.testVarFourth)
        }

    @get:Bindable
    var testAnswer: String = _testAnswer
        set(value) {
            _testAnswer = value
            field = value
            notifyPropertyChanged(BR.testAnswer)
        }

    fun isEmptyEditText(data: String): ErrorsCatcher {
        return if (data.isEmpty()) {
            ErrorsCatcher.EMPTY_FIELD
        } else ErrorsCatcher.NO
    }

    fun isEmpty(): Boolean {
        return _testQuestion.isEmpty() ||
                _testVarFirst.isEmpty() ||
                _testVarSecond.isEmpty() ||
                _testVarThird.isEmpty() ||
                _testVarFourth.isEmpty() ||
                _testAnswer.isEmpty()
    }

}