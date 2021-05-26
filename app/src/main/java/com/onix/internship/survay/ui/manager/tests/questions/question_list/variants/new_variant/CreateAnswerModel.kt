package com.onix.internship.survay.ui.manager.tests.questions.question_list.variants.new_variant

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.onix.internship.survay.util.ErrorsCatcher
import kotlinx.android.parcel.Parcelize

@Parcelize
class CreateAnswerModel(
    private var _questionAnswerVar: String = "",
    private var _questionAnswerScore: String = "",
) : BaseObservable(), Parcelable {

    @get:Bindable
    var questionAnswerVar: String = _questionAnswerVar
        set(value) {
            _questionAnswerVar = value
            field = value
            notifyPropertyChanged(BR.questionAnswerVar)
        }

    @get:Bindable
    var questionAnswerScore: String = _questionAnswerScore
        set(value) {
            _questionAnswerScore = value
            field = value
            notifyPropertyChanged(BR.questionAnswerScore)
        }

    fun isEmptyEditText(data: String): ErrorsCatcher {
        return if (data.isEmpty()) {
            ErrorsCatcher.EMPTY_FIELD
        } else ErrorsCatcher.NO
    }

    fun isEmpty(): Boolean {
        return _questionAnswerVar.isEmpty() || _questionAnswerScore.isEmpty()
    }
}