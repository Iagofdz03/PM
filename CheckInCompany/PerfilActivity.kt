package com.example.checkincompany

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.content.Intent

class PerfilActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        etNombre = findViewById(R.id.etNombre)
        btnGuardar = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            if(nombre.isNotEmpty()){
                Toast.makeText(this, "Perfil actualizado: $nombre", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Introduce un nombre válido", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_inicio -> startActivity(Intent(this, MainActivity::class.java))
            R.id.menu_avisos -> startActivity(Intent(this, AvisosActivity::class.java))
            R.id.menu_perfil -> startActivity(Intent(this, PerfilActivity::class.java))
            R.id.menu_cerrar -> Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }


}
