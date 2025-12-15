package com.example.parquesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parquesapp.R

// MainActivity: contenedor de fragmentos
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Cargamos el layout que contiene un FrameLayout para el Fragment
        setContentView(R.layout.activity_main)

        // Solo añadimos el fragment si no estaba añadido (ejecuciones de rotación)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FirstFragment())
                .commit()
        }
    }
}
