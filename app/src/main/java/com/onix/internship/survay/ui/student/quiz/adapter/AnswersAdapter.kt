package com.onix.internship.survay.ui.student.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.database.tables.answers.Answer
import com.onix.internship.survay.databinding.VariantItemBinding

class AnswersAdapter(
    private val listener: OnItemClick
) : ListAdapter<Answer, ViewHolder>(DiffUtilCallback()) {

    private var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: VariantItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.variant_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.variantItem.setOnClickListener {
            listener.onItemClick(holder.binding.variantItem, getItem(position))
            selectedItemPosition = position
            notifyDataSetChanged()
        }
        if (selectedItemPosition == position) {
            holder.binding.variantItem.background =
                getDrawable(holder.itemView.context, R.drawable.selected_option_bg)
        } else {
            holder.binding.variantItem.background =
                getDrawable(holder.itemView.context, R.drawable.default_option_bg)
        }
    }

}

class ViewHolder(val binding: VariantItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(answer: Answer) {
        with(binding) {
            variantItem.text = answer.text
        }
    }
}