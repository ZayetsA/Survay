package com.onix.internship.survay.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.onix.internship.survay.R
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.databinding.FragmentAdminBinding


class AdminFragment : Fragment() {

    private val userListViewModel: AdminViewModel by viewModels {
        AdminViewModelFactory(
            SurvayDatabase.getInstance(requireContext())
        )
    }
    private lateinit var binding: FragmentAdminBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminBinding.inflate(inflater)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolBar()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = userListViewModel
        super.onViewCreated(view, savedInstanceState)
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
            binding.userListRecycleView.adapter = UserListAdapter(it, userListViewModel)
        })
    }
}