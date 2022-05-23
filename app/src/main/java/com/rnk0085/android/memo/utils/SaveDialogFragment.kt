package com.rnk0085.android.memo.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.rnk0085.android.memo.R

class SaveDialogFragment : DialogFragment() {
    private lateinit var listener: SaveDialogListener

    interface SaveDialogListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment
        listener = fragment as? SaveDialogListener
            ?: throw ClassCastException("$context must implement SaveDialogListener")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setMessage(R.string.save_dialog_title)
                .setPositiveButton(R.string.save) { _, _ ->
                    listener.onDialogPositiveClick()
                }
                .setNegativeButton(R.string.delete) { _, _ ->
                    listener.onDialogNegativeClick()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
