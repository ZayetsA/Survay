package com.onix.internship.survay.ui.manager.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.onix.internship.survay.R
import com.onix.internship.survay.databinding.FragmentManagerBinding

class ManagerFragment : Fragment() {

    private val viewModel: ManagerViewModel by viewModels {
        ManagerViewModelFactory()
    }
    private lateinit var binding: FragmentManagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolBar()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
    }

    private fun setupToolBar() {
        with(binding.managerContainerToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = context.getString(R.string.manager_page_toolbar_title)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}