package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.celestialjewels.CartAdapter
import com.example.celestialjewels.HomePage
import com.example.celestialjewels.Jewelry
import com.example.celestialjewels.Profile
import com.example.celestialjewels.R
import com.example.celestialjewels.Shop
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Cart : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var totalText: TextView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartItems: MutableList<Jewelry>
    private lateinit var bottomNavigationView: BottomNavigationView

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
            showOrderSummaryDialog()
        }

        setupBottomNavigation()
    }

    private fun updateTotal() {
        val total = cartItems.sumOf { it.price * it.quantity }
        totalText.text = "Total: ₱${String.format("%.2f", total)}"
    }

    private fun showOrderSummaryDialog() {
        val orderSummary = StringBuilder()
        cartItems.forEach { item ->
            orderSummary.append("${item.name} x${item.quantity} - ₱${String.format("%.2f", item.price * item.quantity)}\n")
        }
        orderSummary.append("\nTotal: ${totalText.text}")

        MaterialAlertDialogBuilder(this)
            .setTitle("Order Summary")
            .setMessage(orderSummary.toString())
            .setPositiveButton("Confirm") { _, _ ->
                showOrderSuccessDialog()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showOrderSuccessDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Order Placed Successfully!")
            .setMessage("Your order has been placed successfully. Thank you for shopping with us!")
            .setPositiveButton("OK") { _, _ ->
                val intent = Intent(this, HomePage::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            .show()
    }

    private fun setupBottomNavigation() {
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
