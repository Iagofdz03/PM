package com.example.perfilscroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import android.widget.Button
import android.widget.RatingBar
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cargar GIF animado en la ImageView correspondiente
        val gifImageView = findViewById<ImageView>(R.id.gifImageView)
        Glide.with(this)
            .asGif()
            .load(R.drawable.piplup_comiendo) // tu GIF en drawable
            .into(gifImageView)


    }
}
