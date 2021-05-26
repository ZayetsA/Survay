package com.onix.internship.survay.ui.manager.tests.questions.new_question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.onix.internship.survay.R
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.databinding.FragmentCreateQuestionBinding

class CreateQuestionFragment : Fragment() {
    private val args: CreateQuestionFragmentArgs by navArgs()

    private val viewModel: CreateQuestionViewModel by viewModels {
        CreateQuestionViewModelFactory(
            SurvayDatabase.getInstance(requireContext()),
            args.test
        )
    }
    private lateinit var binding: FragmentCreateQuestionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateQuestionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        setupToolBar()
        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
    }

    private fun setupToolBar() {
        with(binding.createQuestionToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = context.getString(R.string.add_question_title)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}