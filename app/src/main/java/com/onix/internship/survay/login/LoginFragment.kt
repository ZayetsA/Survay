package com.onix.internship.survay.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.onix.internship.survay.R
import com.onix.internship.survay.adapter.errorMessage
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.database.UserDatabase
import com.onix.internship.survay.databinding.FragmentLoginBinding
import com.onix.internship.survay.tab.TabFragmentDirections

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getInstance(application).userDatabaseDao
        val repository = RegisterRepository(dao)
        val factory = LoginViewModelFactory(repository, application)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        errorsNotificationsListener()
        removeErrorsDisplayingListener()
        navigationListener()
        return binding.root
    }

    private fun removeErrorsDisplayingListener() {
        binding.loginContainerInputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.loginContainerInputPasswordLayout.errorMessage(false)
                binding.loginContainerInputPassword.error = null
            }
        })

        binding.loginContainerInputLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.loginContainerInputLoginLayout.errorMessage(false)
                binding.loginContainerInputLogin.error = null
            }
        })
    }

    private fun navigationListener() {
        viewModel.acceptNavigation.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                navigateUserDetails()
                viewModel.doneNavigationToUserDetails()
            }
        })
    }

    private fun errorsNotificationsListener() {
        viewModel.errorToastUsername.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                binding.loginContainerInputLoginLayout.errorMessage(false)
                binding.loginContainerInputLogin.error = getString(R.string.error_existing_username)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_existing_username),
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.doneNotifyNickNameError()
            }
        })

        viewModel.errorToastInvalidPassword.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                binding.loginContainerInputPasswordLayout.errorMessage(false)
                binding.loginContainerInputPassword.error =
                    getString(R.string.invalid_password_error)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.invalid_password_error),
                    Toast.LENGTH_SHORT
                )
                    .show()
                viewModel.doneNotifyPasswordError()
            }
        })
    }

    private fun navigateUserDetails() {
        val action = TabFragmentDirections.actionTabFragmentToUserList2()
        navigate(action)
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}