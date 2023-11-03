package com.example.dora

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class StepOneActivity : AppCompatActivity() {

    lateinit var textInput: EditText
    lateinit var textNext: TextView
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_one)

        textInput = findViewById(R.id.textInput)
        textNext = findViewById(R.id.textViewThree)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val percent = sharedPreferences.getInt("percent", 0)

        if (percent == 100) {
            startActivity(Intent(this, ChatCharacterActivity::class.java))
        } else {
            val enteredText = sharedPreferences.getString("enderedText", null)

            if (enteredText != null) {
                val intent = Intent(this, StepTwoActivity::class.java)
                intent.putExtra("enderedText", enteredText)
                startActivity(intent)
                finish()
            } else {
                textNext.setOnClickListener {
                    onButtonClick()
                }
            }
        }
    }
    private fun onButtonClick() {
        val enteredText = textInput.text.toString().trim()

        if (enteredText.isNotEmpty()) {
            val editor = sharedPreferences.edit()
            editor.putString("enderedText", enteredText)
            editor.apply()
            val intent = Intent(this, StepTwoActivity::class.java)
            intent.putExtra("enderedText", enteredText)
            startActivity(intent)
            finish()
        } else {
            textInput.error = "Введите имя"
        }
    }
}


