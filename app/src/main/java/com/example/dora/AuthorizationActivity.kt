package com.example.dora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class AuthorizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        val usernameEditText = findViewById<TextInputEditText>(R.id.textInputEmail)
        val passwordEditText = findViewById<TextInputEditText>(R.id.textInputPassword)
        val loginButton = findViewById<Button>(R.id.ButtonAuth)
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == "admin" && password == "admin") {
                val intent = Intent(this, StepOneActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Неверное имя пользователя или пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }
}