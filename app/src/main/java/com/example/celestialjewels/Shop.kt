package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Shop : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var jewelryAdapter: JewelryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        recyclerView = findViewById(R.id.jewelryRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val jewelryList = arrayListOf(
            Jewelry(1, "Diamond Ring", 25000.00, R.drawable.one),
            Jewelry(2, "Gold Necklace", 15000.00, R.drawable.two),
            Jewelry(3, "Pearl Earrings", 8000.00, R.drawable.three),
            Jewelry(4, "Pearl Earrings", 8000.00, R.drawable.four),
            Jewelry(5, "Pearl Earrings", 8000.00, R.drawable.five),
            Jewelry(6, "Pearl Earrings", 8000.00, R.drawable.six)
        )

        jewelryAdapter = JewelryAdapter(jewelryList, this)
        recyclerView.adapter = jewelryAdapter

        setupCartButton()
        setupBottomNavigation()
    }

    private fun setupCartButton() {
        val cartButton = findViewById<ImageButton>(R.id.CartBtn)
        cartButton.setOnClickListener {
            startActivity(Intent(this, Cart::class.java))
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    startActivity(Intent(this, HomePage::class.java))
                    finish()
                    true
                }
                R.id.action_notification -> {
                    // Already in Shop, return true but do nothing
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

        // Highlight the Shop tab
        bottomNavigationView.selectedItemId = R.id.action_notification
    }

}
