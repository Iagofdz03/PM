package com.example.parquesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parquesapp.Park
import com.example.parquesapp.ParquesViewModel
import com.google.android.material.snackbar.Snackbar
import com.example.parquesapp.R

class FirstFragment : Fragment(),
    ConfirmDeleteDialogFragment.OnDeleteConfirmedListener {

    private val viewModel: ParquesViewModel by viewModels()
    private lateinit var adapter: ParquesAdapter
    private lateinit var rvParques: RecyclerView
    private var parquePendienteBorrado: Park? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // CORRECCIÓN: Cambiar a fragment_first.xml
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvParques = view.findViewById(R.id.rvParques)

        adapter = ParquesAdapter(
            parques = emptyList(),
            onItemClick = { parque ->
                Snackbar.make(view, "Has pulsado: ${parque.name}", Snackbar.LENGTH_SHORT).show()
            },
            onItemLongClick = { pos, parque ->
                parquePendienteBorrado = parque
                val dialog = ConfirmDeleteDialogFragment.newInstance(pos, parque.name)
                dialog.show(parentFragmentManager, "ConfirmDeleteDialog")
            }
        )

        rvParques.layoutManager = LinearLayoutManager(requireContext())
        rvParques.adapter = adapter

        viewModel.parques.observe(viewLifecycleOwner) { lista ->
            adapter.actualizarLista(lista)
        }
    }

    override fun onDeleteConfirmed(position: Int) {
        viewModel.borrarParque(position)
        parquePendienteBorrado?.let {
            Snackbar.make(requireView(), "Parque eliminado: ${it.name}", Snackbar.LENGTH_SHORT).show()
        }
        parquePendienteBorrado = null
    }
}
