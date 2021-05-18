package com.onix.internship.survay.ui.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.database.tables.user.User
import com.onix.internship.survay.databinding.UserItemBinding
import com.onix.internship.survay.util.UserRoleStates

class UserListAdapter(
    private val userList: List<User>,
    private val listener: RecyclerViewCheckBoxListener
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: UserItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.user_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.binding.userItemIsTester.setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->
            listener.onCheckBoxClick(holder.binding.userItemIsTester, userList[position])
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

class ViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        with(binding) {
            userItemFirstName.text = user.firstName
            userItemSecondName.text = user.lastName
            userItemUsername.text = user.login
            userItemUserRole.text = user.role.toString()
            userItemIsTester.isChecked = user.role == UserRoleStates.MANAGER
        }
    }
}