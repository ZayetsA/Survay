package com.onix.internship.survay.ui.manager.tests.questions.question_list

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
import com.onix.internship.survay.databinding.FragmentQuestionListBinding
import com.onix.internship.survay.ui.manager.tests.questions.question_list.adapter.QuestionListAdapter

class QuestionListFragment : Fragment() {

    private lateinit var binding: FragmentQuestionListBinding

    private val args: QuestionListFragmentArgs by navArgs()

    private val viewModel: QuestionListViewModel by viewModels {
        QuestionListViewModelFactory(
            args.test,
            SurvayDatabase.getInstance(requireContext())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionListBinding.inflate(inflater)
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
        with(binding.questionListLayoutToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = args.test.testName
            setNavigationOnClickListener { navigate(QuestionListFragmentDirections.actionQuestionListFragment2ToTestListFragment()) }
        }
    }

    private fun initRecyclerView() {
        binding.questionListLayoutRecycleView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.questions.observe(viewLifecycleOwner, {
            binding.questionListLayoutRecycleView.adapter = QuestionListAdapter(it)
        })
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}