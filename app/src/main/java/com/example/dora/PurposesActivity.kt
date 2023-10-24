package com.example.dora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PurposesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purposes)
        val textView: TextView = findViewById(R.id.textViewFive)
        val characterName = intent.getStringExtra("characterName")
        textView.text = "Расскажите, пожалуйста, что вы ищите в общении с $characterName. Это поможет нам персонализовать опыт"
    }
}