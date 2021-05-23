package com.onix.internship.survay.ui.manager.tests.new_test

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
import com.onix.internship.survay.R
import com.onix.internship.survay.adapter.errorMessage
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.databinding.FragmentCreateTestBinding
import com.onix.internship.survay.util.ErrorsCatcher

class CreateTestFragment : Fragment() {

    private val viewModel: CreateTestViewModel by viewModels {
        CreateTestViewModelFactory(
            SurvayDatabase.getInstance(requireContext())
        )
    }
    private lateinit var binding: FragmentCreateTestBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateTestBinding.inflate(inflater)
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
        with(binding.createTestToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = context.getString(R.string.add_test_toolbar_text)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun clearInputs() {
        binding.createTestInputTestName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.createTestInputTestNameLayout.errorMessage(ErrorsCatcher.NO)
            }
        })

        binding.createTestInputTestDescription.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.createTestInputTestDescriptionLayout.errorMessage(ErrorsCatcher.NO)
            }
        })
    }


    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}