package com.onix.internship.survay.ui.student.quiz.adapter

import android.view.View
import com.onix.internship.survay.database.tables.answers.Answer

interface OnItemClick {
    fun onItemClick(view: View, answer: Answer)
}