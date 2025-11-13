package com.example.checkincompany

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar

class AvisoUrgenteDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle("Aviso urgente")
            .setMessage("¿Deseas enviar un aviso urgente al equipo?")
            .setPositiveButton("Aceptar") { _, _ ->
                Toast.makeText(requireActivity(), "Aviso enviado 🚨", Toast.LENGTH_SHORT).show()
                // Actualizar texto en AvisosActivity
                (activity as? AvisosActivity)?.actualizarAviso("🚨 Aviso urgente enviado al equipo")
            }
            .setNegativeButton("Cancelar") { _, _ ->
                Snackbar.make(requireActivity().findViewById(android.R.id.content),
                    "Operación cancelada", Snackbar.LENGTH_SHORT).show()
            }
            .create()
    }
}
