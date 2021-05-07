package com.onix.internship.survay.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.onix.internship.survay.R
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.database.UserDatabase
import com.onix.internship.survay.databinding.FragmentUserListBinding

class UserListFragment : Fragment() {
    private lateinit var userListViewModel: UserListViewModel
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
        val viewModel: UserListViewModel by viewModels { factory }
        userListViewModel = viewModel
        navigationObserver()
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolBar()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = userListViewModel
        super.onViewCreated(view, savedInstanceState)
    }

    private fun navigationObserver() {
        userListViewModel.acceptNavigation.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                val action = UserListFragmentDirections.actionUserListToTabFragment()
                NavHostFragment.findNavController(this).navigate(action)
                userListViewModel.doneNavigating()
            }
        })
    }

    private fun setupToolBar() {
        with(binding.userListLayoutToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = context.getString(R.string.user_list_fragment_toolbar_title)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun initRecyclerView() {
        binding.userListRecycleView.layoutManager = LinearLayoutManager(this.context)
        userListViewModel.usersAndMentors.observe(viewLifecycleOwner, {
            binding.userListRecycleView.adapter = UserListAdapter(it)
        })
    }
}