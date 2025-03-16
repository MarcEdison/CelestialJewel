package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Checkout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val totalPriceTextView = findViewById<TextView>(R.id.totalPrice)
        val confirmButton = findViewById<Button>(R.id.confirmOrderButton)

        // Get total price from Cart activity
        val totalPrice = intent.getStringExtra("TOTAL_PRICE")
        totalPriceTextView.text = totalPrice

        confirmButton.setOnClickListener {
            // You can add further order processing here
            startActivity(Intent(this, OrderSuccess::class.java)) // Navigate to order success screen
            finish()
        }
    }
}
