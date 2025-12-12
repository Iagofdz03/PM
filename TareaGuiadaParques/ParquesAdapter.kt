package com.example.parquesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter responsable de "traducir" la lista de Strings a tarjetas visuales.
class ParquesAdapter(
    private val parques: MutableList<String>,             // Lista mutable (borraremos elementos)
    private val onItemClick: (String) -> Unit,            // Callback para clic normal
    private val onItemLongClick: (Int, String) -> Unit    // Callback para clic largo
) : RecyclerView.Adapter<ParquesAdapter.ParqueViewHolder>() {

    // ViewHolder = referencia a las vistas de UNA tarjeta
    class ParqueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombreParque)
    }

    // Crea la vista de una tarjeta (inflar el XML item_parque)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParqueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_parque, parent, false)
        return ParqueViewHolder(view)
    }

    // Devuelve cuántos parques hay en la lista
    override fun getItemCount(): Int = parques.size

    // Rellena la tarjeta con los datos del parque en la posición X
    override fun onBindViewHolder(holder: ParqueViewHolder, position: Int) {
        val nombre = parques[position]

        // Pinta el nombre en el TextView
        holder.txtNombre.text = nombre

        // CLICK NORMAL → devuelve el nombre al callback
        holder.itemView.setOnClickListener {
            onItemClick(nombre)
        }

        // CLICK LARGO → devuelve posición y nombre al callback
        holder.itemView.setOnLongClickListener {
            val posReal = holder.adapterPosition // por si la lista cambia
            if (posReal != RecyclerView.NO_POSITION) {
                onItemLongClick(posReal, nombre)
            }
            true // indica que hemos gestionado el evento
        }
    }
}
