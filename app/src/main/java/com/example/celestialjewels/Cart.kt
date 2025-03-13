package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Cart : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var totalText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.cartRecyclerView)
        totalText = findViewById(R.id.totalText)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val cartItems = CartManager.getItems()

        val adapter = CartAdapter(cartItems)
        recyclerView.adapter = adapter

        updateTotal(cartItems)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home-> {

                    startActivity(Intent(this, HomePage::class.java))
                    true
                }
                R.id.action_notification-> {

                    startActivity(Intent(this, Shop::class.java))
                    true
                }
                R.id.action_profile -> {
                    startActivity(Intent(this, Profile::class.java))
                    true
                }
                else -> false
            }
        }

    }

    private fun updateTotal(items: List<Jewelry>) {
        val total = items.sumOf { it.price }
        totalText.text = "Total: ₱${String.format("%.2f", total)}"
    }

}