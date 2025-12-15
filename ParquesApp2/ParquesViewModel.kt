package com.example.parquesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parquesapp.Park

class ParquesViewModel : ViewModel() {

    private val _parques = MutableLiveData<List<Park>>()
    val parques: LiveData<List<Park>> = _parques

    init {
        cargarParquesIniciales()
    }

    private fun cargarParquesIniciales() {
        _parques.value = listOf(
            Park("Parque de Castrelos", "Parque grande con lago y auditorio"),
            Park("Parque de Castelao", "Zona verde en Coia"),
            Park("Parque de Navia", "Parque moderno con zonas infantiles"),
            Park("Parque del Castro", "Vistas a la ría, castillo y murallas"),
            Park("Monte dos Pozos", "Mirador y merendero")
        )
    }

    fun borrarParque(posicion: Int) {
        val listaActual = _parques.value?.toMutableList() ?: return
        if (posicion in listaActual.indices) {
            listaActual.removeAt(posicion)
            _parques.value = listaActual
        }
    }
}
