package com.example.dora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PurposesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purposes)
        val textView: TextView = findViewById(R.id.textViewFive)
        val characterName = intent.getStringExtra("characterName")
        textView.text =
            "Расскажите, пожалуйста, что вы ищите в общении с $characterName. Это поможет нам персонализовать опыт"

        val imageByteArray = intent.getByteArrayExtra("imageByteArray")

        val chooseButton = findViewById<TextView>(R.id.chooseButton)
        chooseButton.setOnClickListener {
            val characterName = intent.getStringExtra("characterName")
            val endCharacterIntent = Intent(this, EndCharacterActivity::class.java)
            endCharacterIntent.putExtra("characterName", characterName)

            // Передача массива байтов в активность EndCharacterActivity
            endCharacterIntent.putExtra("imageByteArray", imageByteArray)

            startActivity(endCharacterIntent)
        }
    }
}