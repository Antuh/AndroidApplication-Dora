package com.example.dora

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView

class EndCharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_character)


        val imageByteArray = intent.getByteArrayExtra("imageByteArray")

        if (imageByteArray != null) {
            val selectedImage =
                BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)

            val imageView = findViewById<ImageView>(R.id.your_image_view_id)
            imageView.setImageBitmap(selectedImage)
        }

        var handler = Handler()
        var percent = 0
        val  updateVariable = object : Runnable {
            override fun run() {
                percent++
                val textView: TextView = findViewById(R.id.textViewThree)
                textView.text = "Создание персонажа $percent %"
                handler.postDelayed(this,50)
                if (percent == 95){
                    startActivity(Intent(this@EndCharacterActivity, ChatCharacterActivity::class.java))
                    finish()

                }
            }
        }
        handler.postDelayed(updateVariable,50)
    }
}