package com.example.dora

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

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
            val selectedImage =
                BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
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
                when (selectedButton) {
                    RazrButton -> {
                        if (isNotificationPermissionGranted()) {
                            val characterName = intent.getStringExtra("characterName")
                            val purposeIntent = Intent(this, PurposesActivity::class.java)
                            purposeIntent.putExtra("characterName", characterName)
                            startActivity(purposeIntent)
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                requestNotificationPermission()
                            }
                        }
                    }
                    NoRazrButton -> {
                        val characterName = intent.getStringExtra("characterName")
                        val purposeIntent = Intent(this, PurposesActivity::class.java)
                        purposeIntent.putExtra("characterName", characterName)
                        startActivity(purposeIntent)
                    }
                }
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestNotificationPermission() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Permission Required")
            .setMessage("To show notifications, we need permission to vibrate.")
            .setPositiveButton("OK") { dialog, _ ->
                val intent = Intent()
                intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                intent.putExtra("app_package", packageName)
                intent.putExtra("app_uid", applicationInfo.uid)
                startActivity(intent)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        alertDialog.show()
    }

    private fun isNotificationPermissionGranted(): Boolean {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID)
            return channel?.importance != NotificationManager.IMPORTANCE_NONE
        }
        return true
    }
}