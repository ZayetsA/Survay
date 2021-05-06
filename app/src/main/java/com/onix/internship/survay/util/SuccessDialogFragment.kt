package com.onix.internship.survay.util

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.onix.internship.survay.R

class SuccessDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.success_login_title))
                .setMessage(getString(R.string.success_login_advice))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.success_login_positive_button_text)) { dialog, id ->
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}