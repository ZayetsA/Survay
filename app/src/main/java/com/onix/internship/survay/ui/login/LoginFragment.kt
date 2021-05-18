package com.onix.internship.survay.ui.login

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
import com.onix.internship.survay.adapter.errorMessage
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.databinding.FragmentLoginBinding
import com.onix.internship.survay.util.ErrorsCatcher

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            SurvayDatabase.getInstance(requireContext())
        )
    }
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = loginViewModel
        removeErrorsDisplayingListener()
        loginViewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
    }

    private fun removeErrorsDisplayingListener() {
        binding.loginContainerInputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.loginContainerInputPasswordLayout.errorMessage(ErrorsCatcher.NO)
            }
        })

        binding.loginContainerInputLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.loginContainerInputLoginLayout.errorMessage(ErrorsCatcher.NO)
            }
        })
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}