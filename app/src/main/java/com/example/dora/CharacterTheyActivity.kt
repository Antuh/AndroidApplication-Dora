package com.example.dora

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.io.ByteArrayOutputStream

class CharacterTheyActivity : AppCompatActivity() {
    private lateinit var selectedCharacterImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_he)

        selectedCharacterImageView = findViewById(R.id.selectedCharacterImageView)
        selectedCharacterImageView.visibility = View.INVISIBLE

        val avatar1 = findViewById<ImageView>(R.id.avatar1)
        val avatar2 = findViewById<ImageView>(R.id.avatar2)
        val avatar3 = findViewById<ImageView>(R.id.avatar3)
        val avatar4 = findViewById<ImageView>(R.id.avatar4)
        val avatar5 = findViewById<ImageView>(R.id.avatar5)

        avatar1.setOnClickListener { selectCharacter(R.drawable.femaleonewindowone) }
        avatar2.setOnClickListener { selectCharacter(R.drawable.maleonewindowone) }
        avatar3.setOnClickListener { selectCharacter(R.drawable.femaletwowindowtwo) }
        avatar4.setOnClickListener { selectCharacter(R.drawable.maletwowindowtwo) }
        avatar5.setOnClickListener { selectCharacter(R.drawable.femalethreewindowthree) }

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