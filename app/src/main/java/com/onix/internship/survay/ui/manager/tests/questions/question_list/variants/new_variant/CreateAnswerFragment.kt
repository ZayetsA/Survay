package com.onix.internship.survay.ui.manager.tests.questions.question_list.variants.new_variant

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
import com.onix.internship.survay.databinding.FragmentCreateAnswerBinding

class CreateAnswerFragment : Fragment() {
    private val args: CreateAnswerFragmentArgs by navArgs()


    private val viewModel: CreateAnswerViewModel by viewModels {
        CreateAnswerViewModelFactory(
            SurvayDatabase.getInstance(requireContext()),
            args.question,
            args.test
        )
    }
    private lateinit var binding: FragmentCreateAnswerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAnswerBinding.inflate(inflater)
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
        with(binding.createAnswerLayoutToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = context.getString(R.string.create_answer_toolbar_text)
            setNavigationOnClickListener {
                navigate(
                    CreateAnswerFragmentDirections.actionCreateAnswerFragmentToVariantsListFragment2(
                        args.question,
                        args.test
                    )
                )
            }
        }
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}