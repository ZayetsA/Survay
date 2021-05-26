package com.onix.internship.survay.ui.student.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.databinding.TestItemBinding

class ResultListAdapter(
    private val listener: TestItemOnClickListener
) : ListAdapter<Tests, ViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TestItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.test_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.testItemListView.setOnClickListener {
            listener.onItemClick(getItem(position))
        }
    }
}

class ViewHolder(val binding: TestItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tests: Tests) {
        with(binding) {
            testItemTestName.text = tests.testName
            testItemTestDesc.text = tests.testDescriptor
        }
    }
}