package com.rnk0085.android.memo.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.rnk0085.android.memo.R

class ErrorDialogFragment : DialogFragment() {
    private lateinit var listener: ErrorDialogListener

    interface ErrorDialogListener {
        fun onErrorDialogPositiveClick()
        fun onErrorDialogNegativeClick()
        fun onErrorDialogCancel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragment as? ErrorDialogListener
            ?: throw ClassCastException("$context must implement ErrorDialogListener")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setMessage(R.string.error_dialog_message)
                .setPositiveButton(R.string.retry) { _, _ ->
                    listener.onErrorDialogPositiveClick()
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                    listener.onErrorDialogNegativeClick()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCancel(dialog: DialogInterface) {
        listener.onErrorDialogCancel()
        super.onCancel(dialog)
    }

    companion object {
        val TAG: String = ErrorDialogFragment::class.java.name

        fun newInstance() = ErrorDialogFragment()
    }
}
