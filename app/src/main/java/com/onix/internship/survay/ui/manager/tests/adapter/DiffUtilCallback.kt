package com.onix.internship.survay.ui.manager.tests.adapter

import androidx.recyclerview.widget.DiffUtil
import com.onix.internship.survay.database.tables.tests.Tests

class DiffUtilCallback : DiffUtil.ItemCallback<Tests>() {
    override fun areItemsTheSame(oldItem: Tests, newItem: Tests): Boolean {
        return oldItem.testId == newItem.testId
    }

    override fun areContentsTheSame(oldItem: Tests, newItem: Tests): Boolean {
        return oldItem == newItem
    }
}