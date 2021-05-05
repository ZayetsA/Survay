package com.onix.internship.survay.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.database.User
import com.onix.internship.survay.databinding.UserItemBinding

class UserListAdapter(private val userList: List<User>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: UserItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.user_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

class ViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        with(binding) {
            userItemFirstName.text = user.firstName
            userItemSecondName.text = user.lastName
            userItemUsername.text = user.login
            userItemUserRole.text = user.role.toString()
        }
    }
}