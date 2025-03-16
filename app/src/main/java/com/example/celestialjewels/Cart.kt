package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Cart : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var totalText: TextView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartItems: MutableList<Jewelry>
    private lateinit var bottomNavigationView: BottomNavigationView  // Add this line

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.cartRecyclerView)
        totalText = findViewById(R.id.totalText)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch unique items from CartManager
        cartItems = CartManager.getUniqueItems()

        // Initialize adapter with updateTotal function
        cartAdapter = CartAdapter(cartItems) { updateTotal() }
        recyclerView.adapter = cartAdapter

        updateTotal()

        val checkoutButton: Button = findViewById(R.id.btncart1)
        checkoutButton.setOnClickListener {
            val intent = Intent(this, Checkout::class.java)
            intent.putExtra("TOTAL_PRICE", totalText.text.toString()) // Pass total price
            startActivity(intent)
        }

        setupBottomNavigation() // Call this function to enable navigation
    }

    private fun updateTotal() {
        val total = cartItems.sumOf { it.price * it.quantity }
        totalText.text = "Total: ₱${String.format("%.2f", total)}"
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Highlight the Cart tab
        bottomNavigationView.selectedItemId = R.id.action_notification

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    startActivity(Intent(this, HomePage::class.java))
                    finish()
                    true
                }
                R.id.action_notification -> {
                    startActivity(Intent(this, Shop::class.java))
                    finish()
                    true
                }
                R.id.action_profile -> {
                    startActivity(Intent(this, Profile::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

}
