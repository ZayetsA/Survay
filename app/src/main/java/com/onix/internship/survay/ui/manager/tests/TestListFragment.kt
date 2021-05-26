package com.onix.internship.survay.ui.manager.tests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.onix.internship.survay.R
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.databinding.FragmentTestListBinding
import com.onix.internship.survay.ui.manager.tests.adapter.TestListAdapter

class TestListFragment : Fragment() {
    private lateinit var binding: FragmentTestListBinding


    private val adapter = TestListAdapter {
        navigate(TestListFragmentDirections.actionTestListFragmentToQuestionListFragment2(it))
    }

    private val viewModel: TestViewModel by viewModels {
        TestViewModelFactory(
            SurvayDatabase.getInstance(requireContext())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestListBinding.inflate(inflater)
        initRecyclerView()
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
        with(binding.testListLayoutToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = context.getString(R.string.manager_testlist_toolbar_title)
            setNavigationOnClickListener { navigate(TestListFragmentDirections.actionTestListFragmentToManagerFragment()) }
        }
    }

    private fun initRecyclerView() {
        binding.testListRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.testListRecycleView.adapter = adapter
        viewModel.testsList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}