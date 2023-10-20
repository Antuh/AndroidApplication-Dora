package com.example.dora

import android.content.Context
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


class Chat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val imageByteArray = intent.getByteArrayExtra("imageByteArray")

        if (imageByteArray != null) {
            val selectedImage = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
            val imageView = findViewById<ImageView>(R.id.your_image_view_id)
            imageView.setImageBitmap(selectedImage)

            sharedPreferences.edit().putBoolean("imageLoaded", true).apply()
        }
    }
}