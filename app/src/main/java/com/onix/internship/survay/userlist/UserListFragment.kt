package com.onix.internship.survay.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.database.UserDatabase
import com.onix.internship.survay.databinding.FragmentUserListBinding

class UserListFragment : Fragment() {
    private lateinit var viewModel: UserListViewModel
    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserListBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).userDatabaseDao
        val repository = RegisterRepository(dao)
        val factory = UserListViewModelFactory(repository, application)
        viewModel = ViewModelProvider(this, factory).get(UserListViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.acceptNavigation.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                val action = UserListFragmentDirections.actionUserListToTabFragment()
                NavHostFragment.findNavController(this).navigate(action)
                viewModel.doneNavigating()
            }
        })

        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.userListRecycleView.layoutManager = LinearLayoutManager(this.context)
        viewModel.usersAndMentors.observe(viewLifecycleOwner, {
            binding.userListRecycleView.adapter = UserListAdapter(it)
        })
    }
}