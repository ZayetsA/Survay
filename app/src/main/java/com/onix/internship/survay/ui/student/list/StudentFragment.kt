package com.onix.internship.survay.ui.student.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.onix.internship.survay.R
import com.onix.internship.survay.database.SurvayDatabase
import com.onix.internship.survay.databinding.FragmentStudentBinding

class StudentFragment : Fragment() {

    private val viewModel: StudentViewModel by viewModels {
        StudentViewModelFactory(
            SurvayDatabase.getInstance(requireContext())
        )
    }
    private lateinit var binding: FragmentStudentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentBinding.inflate(inflater)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        setupToolBar()
        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
    }

    private fun setupToolBar() {
        with(binding.testListLayoutToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = context.getString(R.string.test_list_fragment_toolbar_title)
            setNavigationOnClickListener { navigate(StudentFragmentDirections.actionStudentFragmentToTabFragment()) }
        }
    }

    private fun initRecyclerView() {
        binding.testListRecycleView.layoutManager = LinearLayoutManager(this.context)
        viewModel.test.observe(viewLifecycleOwner, {
            binding.testListRecycleView.adapter = ResultListAdapter(it, viewModel)
        })
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}