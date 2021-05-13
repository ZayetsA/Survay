package com.onix.internship.survay.signup

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
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.database.UserDatabase
import com.onix.internship.survay.databinding.FragmentSignupBinding
import com.onix.internship.survay.util.ErrorsCatcher

class SignupFragment : Fragment() {

    private val signupViewModel: SignupViewModel by viewModels {
        SignUpViewModelFactory(
            RegisterRepository(UserDatabase.getInstance(requireContext()).userDatabaseDao)
        )
    }
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater)
        removeErrorsDisplayingListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = signupViewModel
        signupViewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
    }

    private fun removeErrorsDisplayingListener() {
        binding.signupContainerInputFirstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.signupContainerInputFirstNameLayout.errorMessage(ErrorsCatcher.NO)
            }
        })

        binding.signupContainerInputLastName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.signupContainerInputLastNameLayout.errorMessage(ErrorsCatcher.NO)
            }
        })

        binding.signupContainerInputLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.signupContainerInputLoginLayout.errorMessage(ErrorsCatcher.NO)
            }
        })

        binding.signupContainerInputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.signupContainerInputPasswordLayout.errorMessage(ErrorsCatcher.NO)
            }
        })

        binding.signupContainerInputPasswordConfirmation.addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.signupContainerInputPasswordConfirmationLayout.errorMessage(ErrorsCatcher.NO)
            }
        })
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}