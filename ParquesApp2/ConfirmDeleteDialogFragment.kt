package com.example.parquesapp

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ConfirmDeleteDialogFragment : DialogFragment() {

    interface OnDeleteConfirmedListener {
        fun onDeleteConfirmed(position: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val position = requireArguments().getInt(ARG_POSITION)
        val name = requireArguments().getString(ARG_NAME, "este parque")

        return AlertDialog.Builder(requireContext())
            .setTitle("Borrar parque")
            .setMessage("¿Seguro que quieres borrar \"$name\"?")
            .setPositiveButton("Sí") { _, _ ->
                (parentFragment as? OnDeleteConfirmedListener)
                    ?.onDeleteConfirmed(position)
            }
            .setNegativeButton("Cancelar", null)
            .create()
    }

    companion object {
        private const val ARG_POSITION = "position"
        private const val ARG_NAME = "name"

        fun newInstance(position: Int, name: String): ConfirmDeleteDialogFragment {
            val fragment = ConfirmDeleteDialogFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            args.putString(ARG_NAME, name)
            fragment.arguments = args
            return fragment
        }
    }
}
