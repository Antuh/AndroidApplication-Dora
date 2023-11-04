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
    private var characterOne: String = "неопределенный"
    private var characterTwo: String = "неопределенный"
    private var characterThree: String = "неопределенный"

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
            intent.putExtra("characterOne", characterOne)
            intent.putExtra("characterTwo", characterTwo)
            intent.putExtra("characterThree", characterThree)
            startActivity(intent)
        }

        val seekBarOne = findViewById<SeekBar>(R.id.seekBarOne)
        seekBarOne.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                characterOne = if (progress in 0..50) {
                    "стеснительный"
                } else {
                    "раскрепощенный"
                }
                sharedPreferences.edit().putString("characterOne", characterOne).apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Пользователь начинает перемещать ползунок
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Пользователь отпускает ползунок после перемещения
            }
        })

        val seekBarTwo = findViewById<SeekBar>(R.id.seekBarTwo)
        seekBarTwo.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                characterTwo = if (progress in 0..50) {
                    "пессимистичный"
                } else {
                    "оптимистичный"
                }

                sharedPreferences.edit().putString("characterTwo", characterTwo).apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Пользователь начинает перемещать ползунок
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Пользователь отпускает ползунок после перемещения
            }
        })

        val seekBarThree = findViewById<SeekBar>(R.id.seekBarThree)
        seekBarThree.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                characterTwo = if (progress in 0..50) {
                    "обычный"
                } else {
                    "таинственный"
                }

                sharedPreferences.edit().putString("characterThree", characterThree).apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Пользователь начинает перемещать ползунок
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Пользователь отпускает ползунок после перемещения
            }
        })
    }
}
