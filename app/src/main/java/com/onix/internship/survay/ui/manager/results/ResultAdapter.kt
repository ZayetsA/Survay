package com.onix.internship.survay.ui.manager.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.database.tables.results.Result
import com.onix.internship.survay.database.tables.tests.Tests
import com.onix.internship.survay.database.tables.user.User
import com.onix.internship.survay.databinding.ResultItemBinding
import java.util.*

class ResultAdapter(
    private val testList: List<Tests>,
    private val users: List<User>,
    private val resultList: List<Result>
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ResultItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.result_item, parent, false)
        return ViewHolder(binding, testList, users)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    override fun getItemCount(): Int {
        return resultList.size
    }
}

class ViewHolder(
    val binding: ResultItemBinding,
    private val testList: List<Tests>,
    val users: List<User>
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(result: Result) {
        with(binding) {
            resultItemUserName.text = getUserName(result.resultUserId)?.login
            resultItemTestName.text = getTestName(result.resultTestId)?.testName
            resultItemDate.text = Date(result.date).toString()
            resultItemResult.text = result.score.toString()
        }
    }

    private fun getTestName(resultTestId: Int): Tests? {
        for (test in testList) {
            if (test.testId == resultTestId) {
                return test
            }
        }
        return null
    }

    private fun getUserName(resultUserId: Int): User? {
        for (user in users) {
            if (user.userId == resultUserId) {
                return user
            }
        }
        return null
    }
}