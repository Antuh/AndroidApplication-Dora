package com.example.dora

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream

class NotificationsActivity : AppCompatActivity() {
    private var selectedButton: Button? = null
    private val NOTIFICATION_CHANNEL_ID = "my_notification_channel_id"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        createNotificationChannel()

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val imageByteArray = intent.getByteArrayExtra("imageByteArray")

        if (imageByteArray != null) {
            val selectedImage = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
            val imageView = findViewById<ImageView>(R.id.your_image_view_id)
            imageView.setImageBitmap(selectedImage)

            sharedPreferences.edit().putBoolean("imageLoaded", true).apply()
        }

        val RazrButton = findViewById<Button>(R.id.selectButtonRazr)
        val NoRazrButton = findViewById<Button>(R.id.selectButtonNoRazr)
        val chooseButton = findViewById<TextView>(R.id.chooseButton)

        RazrButton.setOnClickListener {
            if (selectedButton != RazrButton) {
                selectedButton?.setBackgroundResource(R.drawable.rounded_button)
                selectedButton = RazrButton
                RazrButton.setBackgroundResource(R.drawable.gradient_button)
            }
        }
        NoRazrButton.setOnClickListener {
            if (selectedButton != NoRazrButton) {
                selectedButton?.setBackgroundResource(R.drawable.rounded_button)
                selectedButton = NoRazrButton
                NoRazrButton.setBackgroundResource(R.drawable.gradient_button)
            }
        }
        chooseButton.setOnClickListener {
            if (selectedButton != null) {
                val characterName = intent.getStringExtra("characterName")
                val interestsIntent = Intent(this, InterestsActivity::class.java)
                interestsIntent.putExtra("characterName", characterName)

                // Получение изображения
                val imageView = findViewById<ImageView>(R.id.your_image_view_id)
                val drawable = imageView.drawable
                if (drawable is BitmapDrawable) {
                    val bitmap = drawable.bitmap
                    val stream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    val imageByteArray = stream.toByteArray()

                    // Передача массива байтов в активность InterestsActivity
                    interestsIntent.putExtra("imageByteArray", imageByteArray)
                }

                startActivity(interestsIntent)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null) {
            val name = "My Notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance)
            notificationManager.createNotificationChannel(channel)
        }
    }
}