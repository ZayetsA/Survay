package com.onix.internship.survay.ui.manager.tests

import android.view.View
import com.onix.internship.survay.database.tables.tests.Tests

interface RecycleViewCheckBoxListener {

    fun onAccessChange(view: View, test: Tests)

}