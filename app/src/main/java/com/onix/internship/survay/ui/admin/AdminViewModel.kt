package com.onix.internship.survay.ui.admin

import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.database.tables.user.User
import com.onix.internship.survay.util.UserRoleStates
import kotlinx.coroutines.launch

class AdminViewModel(private val database: SurvayDatabase) : ViewModel(),
    RecyclerViewCheckBoxListener {

    private var _usersAndMentors = MutableLiveData<List<User>>()
    val usersAndMentors: LiveData<List<User>>
        get() = getUsersAndMentors()

    private fun getUsersAndMentors(): MutableLiveData<List<User>> {
        viewModelScope.launch {
            _usersAndMentors.value = database.userDatabaseDao.getUsersAndMentors(
                UserRoleStates.MANAGER,
                UserRoleStates.USER
            )
        }
        return _usersAndMentors
    }

    override fun onRoleChanged(view: View, user: User) {
        val checkBox = view as CheckBox
        if (checkBox.isChecked) {
            viewModelScope.launch {
                database.userDatabaseDao.updateUserRole(UserRoleStates.MANAGER, user.userId)
            }
        } else {
            viewModelScope.launch {
                database.userDatabaseDao.updateUserRole(UserRoleStates.USER, user.userId)
            }
        }
    }
}