package com.example.dora

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class StepTwoActivity : AppCompatActivity() {
    private var selectedButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_two)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("enderedText", "Stranger")
        val textView: TextView = findViewById(R.id.receivedText)
        textView.text = "Привет, $username"
        val HeButton = findViewById<Button>(R.id.selectButtonHe)
        val SheButton = findViewById<Button>(R.id.selectButtonShe)
        val TheyButton = findViewById<Button>(R.id.selectButtonThey)
        val textViewThree = findViewById<TextView>(R.id.textViewThree)

        HeButton.setOnClickListener {
            if (selectedButton != HeButton) {
                selectedButton?.setBackgroundResource(R.drawable.rounded_button)
                selectedButton = HeButton
                HeButton.setBackgroundResource(R.drawable.gradient_button)
            }
        }

        SheButton.setOnClickListener {
            if (selectedButton != SheButton) {
                selectedButton?.setBackgroundResource(R.drawable.rounded_button)
                selectedButton = SheButton
                SheButton.setBackgroundResource(R.drawable.gradient_button)
            }
        }
        TheyButton.setOnClickListener {
            if (selectedButton != TheyButton) {
                selectedButton?.setBackgroundResource(R.drawable.rounded_button)
                selectedButton = TheyButton
                TheyButton.setBackgroundResource(R.drawable.gradient_button)
            }
        }

        textViewThree.setOnClickListener {
            if (selectedButton != null) {
                when (selectedButton) {
                    HeButton -> {
                        val intent = Intent(this, CharacterHeActivity::class.java)
                        startActivityForResult(intent, 1)
                    }
                    SheButton -> {
                        val intent = Intent(this, CharacterSheActivity::class.java)
                        startActivityForResult(intent, 2)
                    }
                    TheyButton -> {
                        val intent = Intent(this, CharacterTheyActivity::class.java)
                        startActivityForResult(intent, 3)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
        }
    }
}