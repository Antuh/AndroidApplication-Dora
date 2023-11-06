package com.example.dora

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class InterestsActivity : AppCompatActivity() {
    val selectedButtons: MutableList<Button> = mutableListOf() // Список для отслеживания выбранных кнопок

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interests)

        val imageByteArray = intent.getByteArrayExtra("imageByteArray")

        val ModaButton = findViewById<Button>(R.id.selectButtonModa)
        val KinoButton = findViewById<Button>(R.id.selectButtonKino)
        val RisovButton = findViewById<Button>(R.id.selectButtonRisov)
        val DisainButton = findViewById<Button>(R.id.selectButtonDisain)
        val MusicButton = findViewById<Button>(R.id.selectButtonMusic)
        val TanecButton = findViewById<Button>(R.id.selectButtonTanec)
        val chooseButton = findViewById<TextView>(R.id.chooseButton)

        val gradientButtonDrawable = R.drawable.gradient_button
        val roundedButtonDrawable = R.drawable.rounded_button

        val buttons = arrayOf(ModaButton, KinoButton, RisovButton, DisainButton, MusicButton, TanecButton)

        // Восстановление выбранных интересов из настроек
        restoreSelectedInterests()

        for (i in buttons.indices) {
            buttons[i].setOnClickListener {
                if (selectedButtons.contains(buttons[i])) {
                    selectedButtons.remove(buttons[i])
                    buttons[i].setBackgroundResource(roundedButtonDrawable)
                } else {
                    if (selectedButtons.size < 5) {
                        selectedButtons.add(buttons[i])
                        buttons[i].setBackgroundResource(gradientButtonDrawable)
                    }
                }
            }
        }

        chooseButton.setOnClickListener {
            if (selectedButtons.size == 5) {
                val characterName = intent.getStringExtra("characterName")
                val purposeIntent = Intent(this, PurposesActivity::class.java)
                purposeIntent.putExtra("characterName", characterName)

                // Передача массива байтов в следующую активность
                purposeIntent.putExtra("imageByteArray", imageByteArray)
                saveSelectedInterests()
                startActivity(purposeIntent)
            }
        }
    }

    private fun restoreSelectedInterests() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val selectedInterestsText = sharedPreferences.getString("selectedInterests", "")
        val selectedInterests = selectedInterestsText?.split(",") ?: emptyList()

        val buttons = arrayOf(
            findViewById<Button>(R.id.selectButtonModa),
            findViewById<Button>(R.id.selectButtonKino),
            findViewById<Button>(R.id.selectButtonRisov),
            findViewById<Button>(R.id.selectButtonDisain),
            findViewById<Button>(R.id.selectButtonMusic),
            findViewById<Button>(R.id.selectButtonTanec)
        )

        for (i in buttons.indices) {
            if (selectedInterests.contains(buttons[i].text.toString())) {
                selectedButtons.add(buttons[i])
                buttons[i].setBackgroundResource(R.drawable.gradient_button)
            }
        }
    }

    private fun saveSelectedInterests() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val selectedInterestsText = selectedButtons.map { it.text.toString() }.joinToString(",")
        editor.putString("selectedInterests", selectedInterestsText)

        editor.apply()
    }
}