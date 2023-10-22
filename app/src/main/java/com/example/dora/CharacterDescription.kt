package com.example.dora

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView


class CharacterDescription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_description)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val imageByteArray = intent.getByteArrayExtra("imageByteArray")

        if (imageByteArray != null) {
            val selectedImage = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
            val imageView = findViewById<ImageView>(R.id.your_image_view_id)
            imageView.setImageBitmap(selectedImage)

            sharedPreferences.edit().putBoolean("imageLoaded", true).apply()
        }
        val button = findViewById<TextView>(R.id.chooseButton)
        button.setOnClickListener {
            val intent = Intent(this, CharacterNameActivity::class.java)
            intent.putExtra("imageByteArray", imageByteArray)
            startActivity(intent)
        }


        val seekBarOne = findViewById<SeekBar>(R.id.seekBarOne)
        seekBarOne.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Обработка изменений значения ползунка
                // "progress" содержит текущее значение ползунка
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //пользователь начинает перемещать ползунок
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //пользователь отпускает ползунок после перемещения
            }
        })
    }
}
