package com.onix.internship.survay.ui.student.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private val args: QuizFragmentArgs by navArgs()

    private val viewModel: QuizViewModel by viewModels {
        QuizViewModelFactory(
            SurvayDatabase.getInstance(requireContext()), args.testId
        )
    }
    private lateinit var binding: FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}