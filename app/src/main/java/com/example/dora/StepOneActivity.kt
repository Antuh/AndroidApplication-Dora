package com.example.dora

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class StepOneActivity : AppCompatActivity() {
    private lateinit var textInput: EditText
    private lateinit var textNext: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_one)
        textInput = findViewById(R.id.textInput)
        textNext = findViewById(R.id.textViewThree)
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)

        val enteredText = sharedPreferences.getString("enteredText", null)
        val savedName = sharedPreferences.getString("characterName", null)

        //val editor = sharedPreferences.edit()
        //editor.remove("enteredText")
        //editor.apply()

        if (savedName != null) {
            val intent = Intent(this, PurposesActivity::class.java)
            intent.putExtra("characterName", savedName)
            startActivity(intent)
        }
        if (enteredText != null) {
            val intent = Intent(this, StepTwoActivity::class.java)
            intent.putExtra("textToPass", enteredText)
            startActivity(intent)
            finish()
        } else {
            textNext.setOnClickListener {
                onButtonClick(textInput)
            }
        }
    }

    fun onButtonClick(view: View) {
        val enteredText = textInput.text.toString().trim()

        if (enteredText.isNotEmpty()) {
            val editor = sharedPreferences.edit()
            editor.putString("enteredText", enteredText)
            editor.apply()

            val intent = Intent(this, StepTwoActivity::class.java)
            intent.putExtra("textToPass", enteredText)
            startActivity(intent)
            finish()
        } else {
            textInput.error = "Введите имя"
        }
    }
}