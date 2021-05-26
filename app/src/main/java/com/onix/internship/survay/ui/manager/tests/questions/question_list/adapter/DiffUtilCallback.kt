package com.onix.internship.survay.ui.manager.tests.questions.question_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.onix.internship.survay.database.tables.questions.Question

class DiffUtilCallback : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem.testId == newItem.testId
    }

    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem == newItem
    }
}