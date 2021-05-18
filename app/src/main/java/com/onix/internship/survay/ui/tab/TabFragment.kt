package com.onix.internship.survay.ui.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.onix.internship.survay.R
import com.onix.internship.survay.adapter.ViewPagerAdapter
import com.onix.internship.survay.databinding.FragmentTabBinding
import com.onix.internship.survay.ui.login.LoginFragment
import com.onix.internship.survay.ui.signup.SignupFragment

class TabFragment : Fragment() {
    private val viewModel: TabViewModel by viewModels()
    private lateinit var binding: FragmentTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        setupViewPager(binding.mainContainerTabViewPager)
        binding.mainContainerTabLayout.setupWithViewPager(binding.mainContainerTabViewPager)
    }

    private fun setupViewPager(viewpager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(LoginFragment(), getString(R.string.login_tab_description))
        adapter.addFragment(SignupFragment(), getString(R.string.register_tab_description))
        viewpager.adapter = adapter
    }

}