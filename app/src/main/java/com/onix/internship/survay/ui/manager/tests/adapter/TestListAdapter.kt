package com.onix.internship.survay.ui.manager.tests.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.databinding.TestDetailItemBinding

class TestListAdapter(private val listener: ((Tests) -> Unit)? = null) :
    ListAdapter<Tests, ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TestDetailItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.test_detail_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class ViewHolder(val binding: TestDetailItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tests: Tests, listener: ((Tests) -> Unit)?) {
        with(binding) {
            testDetailItemTestName.text = tests.testName
            testDetailItemTestDesc.text = tests.testDescriptor
            testDetailItemListVIew.setOnClickListener { listener?.invoke(tests) }
        }
    }
}