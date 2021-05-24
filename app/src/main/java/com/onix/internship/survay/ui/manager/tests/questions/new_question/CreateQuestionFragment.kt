package com.onix.internship.survay.ui.manager.tests.questions.new_question

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.onix.internship.survay.R
import com.onix.internship.survay.adapter.errorMessage
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.databinding.FragmentCreateQuestionBinding
import com.onix.internship.survay.util.ErrorsCatcher

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
        clearInputs()
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

    private fun clearInputs() {
        binding.createQuestionInputQuestion.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.createQuestionInputQuestionLayout.errorMessage(ErrorsCatcher.NO)
            }
        })

        binding.createQuestionInputFirstVar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.createQuestionInputFirstVarLayout.errorMessage(ErrorsCatcher.NO)
            }
        })
        binding.createQuestionInputSecondVar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.createQuestionInputSecondVarLayout.errorMessage(ErrorsCatcher.NO)
            }
        })

        binding.createQuestionInputThirdVar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.createQuestionInputThirdVarLayout.errorMessage(ErrorsCatcher.NO)
            }
        })

        binding.createQuestionInputFourthVar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.createQuestionInputFourthVarLayout.errorMessage(ErrorsCatcher.NO)
            }
        })

        binding.createQuestionInputAnswer.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.createQuestionInputAnswerLayout.errorMessage(ErrorsCatcher.NO)
            }
        })
    }


    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}