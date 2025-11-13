package com.example.checkincompany

import android.app.PendingIntent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnEntrada: Button
    private lateinit var btnSalida: Button
    private lateinit var btnNotificacion: Button
    private var employeeId: Int = 0  // ID aleatorio del empleado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEntrada = findViewById(R.id.btnEntrada)
        btnSalida = findViewById(R.id.btnSalida)
        btnNotificacion = findViewById(R.id.btnNotificacion)

        // Generar ID aleatorio de empleado
        employeeId = (1000..9999).random()

        // Botón Entrada
        btnEntrada.setOnClickListener {
            val fechaHora = SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault()).format(Date())
            Toast.makeText(this, "Entrada registrada correctamente: $fechaHora", Toast.LENGTH_SHORT).show()
            it.setBackgroundColor(Color.parseColor("#C8E6C9")) // fondo verde claro
        }

        // Botón Salida
        btnSalida.setOnClickListener {
            val fechaHora = SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault()).format(Date())
            Snackbar.make(it, "Salida registrada: $fechaHora", Snackbar.LENGTH_LONG)
                .setAction("Deshacer") {
                    Toast.makeText(this, "Salida deshecha", Toast.LENGTH_SHORT).show()
                }
                .show()
            it.setBackgroundColor(Color.parseColor("#FFCDD2")) // fondo rojo claro
        }

        // Botón Notificación
        btnNotificacion.setOnClickListener {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelId = "canal_aviso"

            // Crear canal de notificación para Android 8+
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, "Avisos", NotificationManager.IMPORTANCE_DEFAULT)
                manager.createNotificationChannel(channel)
            }

            val intent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification) // <-- crea este drawable
                .setContentTitle("CheckIn Company")
                .setContentText("Nuevo comunicado interno disponible")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            manager.notify(1, builder.build())
        }
    }

    // -------------------
    // Menú global
    // -------------------
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
