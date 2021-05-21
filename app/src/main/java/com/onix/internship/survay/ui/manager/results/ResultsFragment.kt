package com.onix.internship.survay.ui.manager.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.onix.internship.survay.R
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.databinding.FragmentResultsBinding


class ResultsFragment : Fragment() {
    private val viewModel: ResultsViewModel by viewModels {
        ResultsViewModelFactory(
            SurvayDatabase.getInstance(requireContext())
        )
    }
    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultsBinding.inflate(inflater)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolBar()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        super.onViewCreated(view, savedInstanceState)
    }


    private fun setupToolBar() {
        with(binding.resultsContainerToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = context.getString(R.string.user_list_fragment_toolbar_title)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun initRecyclerView() {
        binding.resultsContainerRecycleView.layoutManager = LinearLayoutManager(this.context)
        viewModel.isDataLoading.observe(viewLifecycleOwner, {
            binding.resultsContainerRecycleView.adapter = ResultAdapter(
                viewModel.model.tests,
                viewModel.model.users,
                viewModel.model.resultList
            )
        })
    }
}