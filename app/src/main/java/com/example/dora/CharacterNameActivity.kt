package com.example.dora

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class CharacterNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_name)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val imageByteArray = intent.getByteArrayExtra("imageByteArray")

        val imageView = findViewById<ImageView>(R.id.your_image_view_id)
        val button = findViewById<TextView>(R.id.chooseButton)
        val editText = findViewById<EditText>(R.id.textInputs)

        if (imageByteArray != null) {
            val selectedImage = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
            imageView.setImageBitmap(selectedImage)
        }

        button.setOnClickListener {
            val enteredName = editText.text.toString()

            if (enteredName.isNotEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString("characterName", enteredName)
                editor.apply()

                val intent = Intent(this, NotificationsActivity::class.java)
                intent.putExtra("imageByteArray", imageByteArray)
                intent.putExtra("characterName", enteredName)
                startActivity(intent)
            } else {
                editText.error = "Введите имя"
            }
        }
    }
}