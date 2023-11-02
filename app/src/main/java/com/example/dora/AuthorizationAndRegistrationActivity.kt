package com.example.dora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AuthorizationAndRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization_and_registration)

        val registrationButton = findViewById<Button>(R.id.selectButtonRegistration)

        registrationButton.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
        val authorizationButton = findViewById<TextView>(R.id.textViewAuth)
        authorizationButton.setOnClickListener{
            val intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)
        }
    }
}