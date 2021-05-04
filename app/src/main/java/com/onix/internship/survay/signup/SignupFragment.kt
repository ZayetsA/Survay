package com.onix.internship.survay.signup

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.database.RegisterRepository
import com.onix.internship.survay.database.UserDatabase
import com.onix.internship.survay.databinding.FragmentSignupBinding

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

        viewModel.navigate.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                Toast.makeText(requireContext(), "Registration completed!", Toast.LENGTH_SHORT)
                    .show()
                viewModel.doneNavigating()
            }
        })

        viewModel.errorToast.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
                viewModel.doneToast()
            }
        })

        viewModel.errorToastUsername.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(
                    requireContext(),
                    "This username is already taken!",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.doneNotificationUserName()
            }
        })

        viewModel.errorToastPassword.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Passwords did not match!", Toast.LENGTH_SHORT)
                    .show()
                viewModel.donePasswordNotification()
            }
        })
        return binding.root
    }
}