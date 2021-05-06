package com.onix.internship.survay.signup

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.R
import com.onix.internship.survay.adapter.errorMessage
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.database.UserDatabase
import com.onix.internship.survay.databinding.FragmentSignupBinding
import com.onix.internship.survay.util.SuccessDialogFragment

class SignupFragment : Fragment() {

    private lateinit var viewModel: SignupViewModel
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
        viewModel = ViewModelProvider(this, factory).get(SignupViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        errorsNotificationListener()
        removeErrorsDisplayingListener()
        return binding.root
    }

    private fun errorsNotificationListener() {
        viewModel.navigate.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                val myDialogFragment = SuccessDialogFragment()
                val manager = requireActivity().supportFragmentManager
                myDialogFragment.show(manager, getString(R.string.success_dialog_tag))
                viewModel.doneNavigating()
            }
        })

        viewModel.errorExistingUsername.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                binding.signupContainerInputLoginLayout.errorMessage(false)
                binding.signupContainerInputLogin.error =
                    getString(R.string.existing_username_error)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.existing_username_error),
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.doneNotificationUserName()
            }
        })

        viewModel.errorPasswordMatch.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                binding.signupContainerInputPasswordLayout.errorMessage(false)
                binding.signupContainerInputPasswordConfirmationLayout.errorMessage(false)
                binding.signupContainerInputPassword.error =
                    getString(R.string.match_passwords_error)
                binding.signupContainerInputPasswordConfirmation.error =
                    getString(R.string.match_passwords_error)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.match_passwords_error),
                    Toast.LENGTH_SHORT
                )
                    .show()
                viewModel.donePasswordNotification()
            }
        })


        viewModel.errorPasswordDifficult.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                binding.signupContainerInputPasswordLayout.errorMessage(false)
                binding.signupContainerInputPasswordConfirmationLayout.errorMessage(false)
                binding.signupContainerInputPassword.error =
                    getString(R.string.short_password_error)
                binding.signupContainerInputPasswordConfirmation.error =
                    getString(R.string.short_password_error)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.short_password_error),
                    Toast.LENGTH_SHORT
                )
                    .show()
                viewModel.donePasswordDifficultNotification()
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
                binding.signupContainerInputFirstNameLayout.errorMessage(false)
            }
        })

        binding.signupContainerInputLastName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.signupContainerInputLastNameLayout.errorMessage(false)
            }
        })

        binding.signupContainerInputLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.signupContainerInputLoginLayout.errorMessage(false)
            }
        })

        binding.signupContainerInputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.signupContainerInputPasswordLayout.errorMessage(false)
            }
        })

        binding.signupContainerInputPasswordConfirmation.addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.signupContainerInputPasswordConfirmationLayout.errorMessage(false)
            }
        })
    }
}