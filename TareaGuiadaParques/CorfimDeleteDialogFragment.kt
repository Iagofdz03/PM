package com.example.parquesapp

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

// DialogFragment → cuadro de diálogo que puede sobrevivir a rotaciones del móvil
class ConfirmDeleteDialogFragment : DialogFragment() {

    // Interfaz para comunicar el resultado a la Activity
    interface OnDeleteConfirmedListener {
        fun onDeleteConfirmed(position: Int)
    }

    // Se ejecuta al crear el diálogo
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Recuperamos los parámetros enviados al crear el diálogo
        val position = requireArguments().getInt(ARG_POSITION)
        val name = requireArguments().getString(ARG_NAME, "este parque")

        // Construimos el diálogo
        return AlertDialog.Builder(requireContext())
            .setTitle("Borrar parque")
            .setMessage("¿Seguro que quieres borrar \"$name\"?")
            .setPositiveButton("Sí") { _, _ ->
                // Avisamos a la Activity de que confirmaron el borrado
                (activity as? OnDeleteConfirmedListener)
                    ?.onDeleteConfirmed(position)
            }
            .setNegativeButton("Cancelar", null) // No hace nada
            .create()
    }

    companion object {
        private const val ARG_POSITION = "position"
        private const val ARG_NAME = "name"

        // Método recomendado para crear fragments con datos
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
