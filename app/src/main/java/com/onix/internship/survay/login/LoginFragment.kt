package com.onix.internship.survay.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
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

        viewModel.emptyFieldsError.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                viewModel.checkedEmptyFields()
            }
        })

        viewModel.errorToastUsername.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(
                    requireContext(),
                    "User does not exist, please Register!",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.doneNotifyNickNameError()
            }
        })

        viewModel.errorToastInvalidPassword.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Please check your Password", Toast.LENGTH_SHORT)
                    .show()
                viewModel.doneNotifyPasswordError()
            }
        })

        viewModel.acceptNavigation.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                navigateUserDetails()
                viewModel.doneNavigationToUserDetails()
            }
        })

        return binding.root
    }

    private fun navigateUserDetails() {
        val action = TabFragmentDirections.actionTabFragmentToUserList2()
        navigate(action)
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}