package com.example.dora

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.io.ByteArrayOutputStream

class CharacterHeActivity : AppCompatActivity() {
    private lateinit var selectedCharacterImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_he)

        selectedCharacterImageView = findViewById(R.id.selectedCharacterImageView)
        selectedCharacterImageView.visibility = View.INVISIBLE

        val avatar1 = findViewById<ImageView>(R.id.avatar1)
        val avatar2 = findViewById<ImageView>(R.id.avatar2)
        val avatar3 = findViewById<ImageView>(R.id.avatar3)

        avatar1.setOnClickListener { selectCharacter(R.drawable.characterone) }
        avatar2.setOnClickListener { selectCharacter(R.drawable.avatartwo) }
        avatar3.setOnClickListener { selectCharacter(R.drawable.avatarthree) }

        val chooseButton = findViewById<TextView>(R.id.chooseButton)
        chooseButton.setOnClickListener { onChooseButtonClick() }
    }

    private fun selectCharacter(imageResource: Int) {
        selectedCharacterImageView.setImageResource(imageResource)
        selectedCharacterImageView.visibility = View.VISIBLE
    }

    private fun onChooseButtonClick() {
        if (selectedCharacterImageView.visibility == View.VISIBLE) {
            val selectedImage = (selectedCharacterImageView.drawable as BitmapDrawable).bitmap

            val stream = ByteArrayOutputStream()
            selectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()

            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("reachedChatActivity", true).apply()

            val intent = Intent(this, CharacterDescription::class.java)
            intent.putExtra("imageByteArray", byteArray)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        } else {
            AlertDialog.Builder(this)
                .setTitle("Предупреждение")
                .setMessage("Пожалуйста, выберите персонажа перед продолжением.")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}