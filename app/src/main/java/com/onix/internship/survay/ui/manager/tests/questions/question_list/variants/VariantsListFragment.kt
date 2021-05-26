package com.onix.internship.survay.ui.manager.tests.questions.question_list.variants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.onix.internship.survay.R
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.databinding.FragmentVariantsListBinding
import com.onix.internship.survay.ui.manager.tests.questions.question_list.variants.adapter.VariantsListAdapter

class VariantsListFragment : Fragment() {

    private val args: VariantsListFragmentArgs by navArgs()

    private val viewModel: VariantsListViewModel by viewModels {
        VariantsListViewModelFactory(
            SurvayDatabase.getInstance(requireContext()),
            args.question,
            args.test
        )
    }
    private lateinit var binding: FragmentVariantsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVariantsListBinding.inflate(inflater)
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
        with(binding.variantsListLayoutToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title =
                args.question.text
            setNavigationOnClickListener {
                navigate(
                    VariantsListFragmentDirections.actionVariantsListFragment2ToQuestionListFragment2(
                        args.test
                    )
                )
            }
        }
    }

    private fun initRecyclerView() {
        binding.variantsListRecyclerView.layoutManager = LinearLayoutManager(this.context)
        viewModel.answerList.observe(viewLifecycleOwner, {
            binding.variantsListRecyclerView.adapter = VariantsListAdapter(it)
        })
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}
