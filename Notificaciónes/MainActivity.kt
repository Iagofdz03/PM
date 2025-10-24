package com.example.notificaciones

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "parque_channel_id_final"
    private val NOTIFICATION_ID = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnToast: Button = findViewById(R.id.btnToast)
        val btnSnackbar: Button = findViewById(R.id.btnSnackbar)
        val btnNotificacion: Button = findViewById(R.id.btnNotificacion)

        // 🟢 Acción Toast
        btnToast.setOnClickListener {
            Toast.makeText(this, "¡Este es un Toast!", Toast.LENGTH_SHORT).show()
        }

        // 🟢 Acción Snackbar
        btnSnackbar.setOnClickListener { view ->
            val snackbar = Snackbar.make(view, "Este es un Snackbar", Snackbar.LENGTH_LONG)
            snackbar.setAction("Deshacer") {
                Toast.makeText(this, "Acción deshecha", Toast.LENGTH_SHORT).show()
            }
            snackbar.show()
        }

        // 🟢 Acción Notificación
        btnNotificacion.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
                    Toast.makeText(this, "Concede permiso de notificaciones y vuelve a intentarlo", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }

            crearCanalNotificacion()
            enviarNotificacion()
        }
    }

    private fun crearCanalNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notificaciones del Parque"
            val descriptionText = "Canal para avisos del parque"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // 🧹 Eliminar canal antiguo por si estaba silenciado
            notificationManager.deleteNotificationChannel(CHANNEL_ID)

            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun enviarNotificacion() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            else
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Cierre del parque")
            .setContentText("Cierre a las 20:00")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(NOTIFICATION_ID, builder.build())

        Toast.makeText(this, "✅ Notificación enviada", Toast.LENGTH_SHORT).show()
    }
}
