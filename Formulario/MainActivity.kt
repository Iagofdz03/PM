package com.example.formulariointeractivo

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los elementos del layout
        val nombreEditText = findViewById<EditText>(R.id.editTextText)
        val correoEditText = findViewById<EditText>(R.id.editTextText3)
        val checkDeporte = findViewById<CheckBox>(R.id.checkBox)
        val checkMusica = findViewById<CheckBox>(R.id.checkBox2)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupGenero)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val switchNotif = findViewById<Switch>(R.id.switch1)
        val botonEnviar = findViewById<Button>(R.id.button)

        // Configurar el spinner con los valores del array
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.paises,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Evento del botón
        botonEnviar.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val correo = correoEditText.text.toString()

            val deporte = if (checkDeporte.isChecked) "Le gusta el deporte" else "No le gusta el deporte"
            val musica = if (checkMusica.isChecked) "Le gusta la música" else "No le gusta la música"

            val generoSeleccionadoId = radioGroup.checkedRadioButtonId
            val genero = if (generoSeleccionadoId != -1) {
                findViewById<RadioButton>(generoSeleccionadoId).text.toString()
            } else {
                "No especificado"
            }

            val pais = spinner.selectedItem.toString()
            val notificaciones = if (switchNotif.isChecked) "Sí" else "No"

            val mensaje = """
                Nombre: $nombre
                Correo: $correo
                Género: $genero
                País: $pais
                $deporte
                $musica
                Recibir notificaciones: $notificaciones
            """.trimIndent()

            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }
    }
}
