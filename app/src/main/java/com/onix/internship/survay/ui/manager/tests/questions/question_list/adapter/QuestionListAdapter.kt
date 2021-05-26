package com.onix.internship.survay.ui.manager.tests.questions.question_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.database.tables.questions.Question
import com.onix.internship.survay.databinding.QuestionItemBinding


class QuestionListAdapter(private val listener: ((Question) -> Unit)? = null) :
    ListAdapter<Question, ViewHolder>((DiffUtilCallback())) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: QuestionItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.question_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class ViewHolder(val binding: QuestionItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(question: Question, listener: ((Question) -> Unit)?) {
        with(binding) {
            questionItemQuestion.text = question.text
            questionItemView.setOnClickListener { listener?.invoke(question) }
        }
    }
}