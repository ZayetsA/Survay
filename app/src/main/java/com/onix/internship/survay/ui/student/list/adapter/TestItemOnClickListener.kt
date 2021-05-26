package com.onix.internship.survay.ui.student.list.adapter

import com.onix.internship.survay.database.tables.tests.Tests

interface TestItemOnClickListener {
    fun onItemClick(tests: Tests)
}