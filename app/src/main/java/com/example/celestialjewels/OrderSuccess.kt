package com.example.celestialjewels

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OrderSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)

        val backButton = findViewById<Button>(R.id.backToHomeButton)
        backButton.setOnClickListener {
            finish() // Close activity and go back
        }
    }
}
