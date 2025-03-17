package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBttn = findViewById<Button>(R.id.Login)
        val forgotPasswordBttn = findViewById<TextView>(R.id.fgtpass)
        val registerBttn = findViewById<TextView>(R.id.Register)

        // Navigate to HomePage on Login
        loginBttn.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        // Navigate to Forgot Password page
        forgotPasswordBttn.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Navigate to Registration page
        registerBttn.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
    }
}
