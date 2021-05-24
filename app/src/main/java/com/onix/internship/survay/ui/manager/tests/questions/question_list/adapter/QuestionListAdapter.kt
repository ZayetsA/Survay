package com.onix.internship.survay.ui.manager.tests.questions.question_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.database.tables.questions.QuestionAndAnswer
import com.onix.internship.survay.databinding.QuestionItemBinding

class QuestionListAdapter(
    private val userList: List<QuestionAndAnswer>
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: QuestionItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.question_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

class ViewHolder(val binding: QuestionItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(question: QuestionAndAnswer) {
        with(binding) {
            questionItemQuestion.text = question.question.text
        }
    }
}