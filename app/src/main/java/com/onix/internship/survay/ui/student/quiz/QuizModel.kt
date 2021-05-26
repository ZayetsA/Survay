package com.onix.internship.survay.ui.student.quiz

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.onix.internship.survay.database.tables.questions.QuestionAndAnswer
import com.onix.internship.survay.database.tables.user.User

class QuizModel(
    _currentUser: User = User(),
    _questionAndAnswer: List<QuestionAndAnswer> = emptyList()
) : BaseObservable() {

    @Bindable
    var currentUser = _currentUser
        set(value) {
            field = value
            notifyPropertyChanged(BR.currentUser)
        }

    @Bindable
    var questionAndAnswerList = _questionAndAnswer
        set(value) {
            field = value
            notifyPropertyChanged(BR.questionAndAnswerList)
        }
}