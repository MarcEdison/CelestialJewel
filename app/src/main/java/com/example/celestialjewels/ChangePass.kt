package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity



class ChangePass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass)

        Log.d("ChangePass", "ChangePass Activity started")

        val passBackBtn = findViewById<Button>(R.id.passBackbtn)

        // Set click listener to go back to Profile
        passBackBtn.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
            finish() // Close ChangePass activity
        }
    }
}