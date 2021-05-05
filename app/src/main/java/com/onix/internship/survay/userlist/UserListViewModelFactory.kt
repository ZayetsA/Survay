package com.onix.internship.survay.userlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.RegisterRepository

@Suppress("UNCHECKED_CAST")
class UserListViewModelFactory(
    private val repository: RegisterRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}