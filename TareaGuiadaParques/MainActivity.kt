package com.example.parquesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

// La Activity principal, que muestra la lista de parques.
class MainActivity : AppCompatActivity(),
    ConfirmDeleteDialogFragment.OnDeleteConfirmedListener { // Implementa la interfaz del diálogo

    // Referencias al RecyclerView y su adaptador
    private lateinit var rvParques: RecyclerView
    private lateinit var adaptador: ParquesAdapter

    // Lista MUTABLE -> necesaria para poder borrar elementos
    private val listaParques = mutableListOf(
        "Parque de Castrelos",
        "Parque de Castelao",
        "Parque de Navia",
        "Parque del Castro",
        "Monte dos Pozos"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Cargamos el layout XML

        // Enlazamos el RecyclerView del XML
        rvParques = findViewById(R.id.rvParques)

        // Indicamos que la lista será vertical (efecto scroll típico)
        rvParques.layoutManager = LinearLayoutManager(this)

        // Creamos el Adapter pasando:
        // - La lista de parques
        // - El callback de click normal
        // - El callback de click largo
        adaptador = ParquesAdapter(
            parques = listaParques,

            // CLICK NORMAL → solo muestra un Snackbar
            onItemClick = { nombreParque ->
                Snackbar.make(
                    rvParques,
                    "Has pulsado: $nombreParque",
                    Snackbar.LENGTH_LONG
                ).show()
            },

            // CLICK LARGO → abre diálogo de confirmación
            onItemLongClick = { position, nombreParque ->
                val dialog = ConfirmDeleteDialogFragment.newInstance(position, nombreParque)
                dialog.show(supportFragmentManager, "ConfirmDeleteDialog")
            }
        )

        // Conectamos el RecyclerView con su adapter
        rvParques.adapter = adaptador
    }

    // Este método se ejecuta cuando el usuario pulsa "Sí" en el diálogo
    override fun onDeleteConfirmed(position: Int) {

        // Verificamos que la posición existe todavía
        if (position in listaParques.indices) {

            val nombre = listaParques[position] // Guardamos el nombre solo para mostrarlo

            // 1) Borrar el elemento de la lista de datos
            listaParques.removeAt(position)

            // 2) Notificar al adapter que el ítem ha sido eliminado
            adaptador.notifyItemRemoved(position)

            // 3) Mostrar confirmación en pantalla
            Snackbar.make(
                rvParques,
                "Parque eliminado: $nombre",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
