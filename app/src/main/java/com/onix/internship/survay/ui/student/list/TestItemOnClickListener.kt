package com.onix.internship.survay.ui.student.list

import com.onix.internship.survay.database.tables.tests.Tests

interface TestItemOnClickListener {
    fun onItemClick(test: Tests)
}