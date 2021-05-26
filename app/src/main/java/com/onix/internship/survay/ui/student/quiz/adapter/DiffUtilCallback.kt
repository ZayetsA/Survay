package com.onix.internship.survay.ui.student.quiz.adapter

import androidx.recyclerview.widget.DiffUtil
import com.onix.internship.survay.database.tables.answers.Answer

class DiffUtilCallback : DiffUtil.ItemCallback<Answer>() {
    override fun areItemsTheSame(
        oldItem: Answer,
        newItem: Answer
    ): Boolean {
        return oldItem.answerId == newItem.answerId
    }

    override fun areContentsTheSame(
        oldItem: Answer,
        newItem: Answer
    ): Boolean {
        return oldItem == newItem
    }
}