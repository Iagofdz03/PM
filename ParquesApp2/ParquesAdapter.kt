package com.example.parquesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parquesapp.R
import com.example.parquesapp.Park

class ParquesAdapter(
    private var parques: List<Park>,
    private val onItemClick: (Park) -> Unit,
    private val onItemLongClick: (Int, Park) -> Unit
) : RecyclerView.Adapter<ParquesAdapter.ParqueViewHolder>() {

    class ParqueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombreParque)
        val txtDesc: TextView = itemView.findViewById(R.id.txtDescParque)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParqueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_parque, parent, false)
        return ParqueViewHolder(view)
    }

    override fun getItemCount(): Int = parques.size

    override fun onBindViewHolder(holder: ParqueViewHolder, position: Int) {
        val parque = parques[position]
        holder.txtNombre.text = parque.name
        holder.txtDesc.text = parque.desc

        holder.itemView.setOnClickListener { onItemClick(parque) }

        holder.itemView.setOnLongClickListener {
            val posReal = holder.adapterPosition
            if (posReal != RecyclerView.NO_POSITION) {
                onItemLongClick(posReal, parque)
            }
            true
        }
    }

    fun actualizarLista(nuevaLista: List<Park>) {
        parques = nuevaLista
        notifyDataSetChanged()
    }
}
