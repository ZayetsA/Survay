package com.onix.internship.survay.ui.manager.tests

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.databinding.TestDetailItemBinding

class TestListAdapter(
    private val testList: List<Tests>,
    private val listener: TestViewModel
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TestDetailItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.test_detail_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(testList[position])
        holder.binding.testDetailItemTestIsAvailable.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->
            listener.onAccessChange(
                holder.binding.testDetailItemTestIsAvailable,
                testList[position]
            )
        }
    }

    override fun getItemCount(): Int {
        return testList.size
    }
}

class ViewHolder(val binding: TestDetailItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(test: Tests) {
        with(binding) {
            testDetailItemTestName.text = test.testName
            testDetailItemTestDesc.text = test.testDescriptor
            // TODO: 21.05.2021 add isChecked state to checkbox 
        }
    }
}