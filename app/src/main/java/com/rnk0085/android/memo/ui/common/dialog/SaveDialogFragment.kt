package com.rnk0085.android.memo.ui.common.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.rnk0085.android.memo.R

class SaveDialogFragment : DialogFragment() {
    private lateinit var listener: SaveDialogListener

    interface SaveDialogListener {
        fun onSaveDialogPositiveClick()
        fun onSaveDialogNegativeClick()
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
                .setMessage(R.string.save_dialog_message)
                .setPositiveButton(R.string.save) { _, _ ->
                    listener.onSaveDialogPositiveClick()
                }
                .setNegativeButton(R.string.delete) { _, _ ->
                    listener.onSaveDialogNegativeClick()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        val TAG: String = SaveDialogFragment::class.java.name

        fun newInstance() = SaveDialogFragment()
    }
}
