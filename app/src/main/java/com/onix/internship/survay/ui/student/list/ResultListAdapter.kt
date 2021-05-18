package com.onix.internship.survay.ui.student.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.databinding.TestItemBinding

class ResultListAdapter(
    private val testList: List<Tests>,
    private val listener: TestItemOnClickListener
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TestItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.test_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(testList[position])
        holder.binding.userItemListView.setOnClickListener {
            listener.onItemClick(testList[position])
        }
    }

    override fun getItemCount(): Int {
        return testList.size
    }
}

class ViewHolder(val binding: TestItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tests: Tests) {
        with(binding) {
            userItemFirstName.text = tests.testName
        }
    }
}