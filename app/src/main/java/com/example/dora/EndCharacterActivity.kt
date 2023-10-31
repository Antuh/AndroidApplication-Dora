package com.example.dora

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EndCharacterActivity : AppCompatActivity() {
    private val PREF_NAME = "MyPrefs"
    private val PERCENT_KEY = "percent"

    var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_character)

        val sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        var percent = sharedPreferences.getInt(PERCENT_KEY, 0)
        val imageByteArray = intent.getByteArrayExtra("imageByteArray")
        if (imageByteArray != null) {
            val selectedImage = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)

            val imageView = findViewById<ImageView>(R.id.your_image_view_id)
            imageView.setImageBitmap(selectedImage)
        }

        handler = Handler()
        val updateVariable = object : Runnable {
            override fun run() {
                percent++
                val textView: TextView = findViewById(R.id.textViewThree)
                textView.text = "Создание персонажа $percent %"
                handler?.postDelayed(this, 50)
                if (percent == 100) {
                    handler?.removeCallbacks(this)
                    with(sharedPreferences.edit()) {
                        putInt(PERCENT_KEY, percent)
                        apply()
                    }
                    startActivity(Intent(this@EndCharacterActivity, ChatCharacterActivity::class.java))
                    finish()
                } else {
                    with(sharedPreferences.edit()) {
                        putInt(PERCENT_KEY, percent)
                        apply()
                    }
                }
            }
        }
        handler?.postDelayed(updateVariable, 50)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacksAndMessages(null)
    }
}