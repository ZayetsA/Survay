package com.onix.internship.survay.ui.admin

import android.view.View
import com.onix.internship.survay.database.tables.user.User

interface RecyclerViewCheckBoxListener {
    fun onRoleChanged(view: View, user: User)
}