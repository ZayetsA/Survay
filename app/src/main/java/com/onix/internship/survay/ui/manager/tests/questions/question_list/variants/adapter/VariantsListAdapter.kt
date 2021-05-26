package com.onix.internship.survay.ui.manager.tests.questions.question_list.variants.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.database.tables.answers.Answer
import com.onix.internship.survay.databinding.AnswerItemBinding

class VariantsListAdapter(
    private val variantsList: List<Answer>,
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: AnswerItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.answer_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(variantsList[position])
    }

    override fun getItemCount(): Int {
        return variantsList.size
    }
}

class ViewHolder(val binding: AnswerItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(answer: Answer) {
        with(binding) {
            answerItemAnswerName.text = answer.text
            answerItemAnswerScore.text = answer.score.toString()
        }
    }
}