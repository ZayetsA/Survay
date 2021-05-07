package com.onix.internship.survay.signup

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.onix.internship.survay.R
import com.onix.internship.survay.adapter.errorMessage
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.database.UserDatabase
import com.onix.internship.survay.databinding.FragmentSignupBinding
import com.onix.internship.survay.util.ErrorsCatcher
import com.onix.internship.survay.util.SuccessDialogFragment

class SignupFragment : Fragment() {

    private lateinit var signupViewModel: SignupViewModel
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater)
        val application: Application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).userDatabaseDao
        val repository = RegisterRepository(dao)
        val factory = SignUpViewModelFactory(repository, application)
        val viewModel: SignupViewModel by viewModels { factory }
        signupViewModel = viewModel
        errorsNotificationListener()
        removeErrorsDisplayingListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = signupViewModel
        super.onViewCreated(view, savedInstanceState)
    }

    private fun errorsNotificationListener() {
        signupViewModel.navigate.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                val myDialogFragment = SuccessDialogFragment()
                val manager = requireActivity().supportFragmentManager
                myDialogFragment.show(manager, getString(R.string.success_dialog_tag))
                signupViewModel.doneNavigating()
            }
        })
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
}