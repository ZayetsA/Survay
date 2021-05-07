package com.onix.internship.survay.login

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
import com.onix.internship.survay.databinding.FragmentLoginBinding
import com.onix.internship.survay.tab.TabFragmentDirections
import com.onix.internship.survay.util.ErrorsCatcher

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
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
        val viewModel: LoginViewModel by viewModels { factory }
        loginViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = loginViewModel
        removeErrorsDisplayingListener()
        navigationListener()
        super.onViewCreated(view, savedInstanceState)
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

    private fun navigationListener() {
        loginViewModel.acceptNavigation.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                navigateUserDetails()
                loginViewModel.doneNavigationToUserDetails()
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