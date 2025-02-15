package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.celestialjewels.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Shop : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var jewelryAdapter: JewelryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        recyclerView = findViewById(R.id.jewelryRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val jewelryList = ArrayList<Jewelry>()
        jewelryList.add(Jewelry(1, "Diamond Ring", 25000.00, R.drawable.one))
        jewelryList.add(Jewelry(2, "Gold Necklace", 15000.00, R.drawable.two))
        jewelryList.add(Jewelry(3, "Pearl Earrings", 8000.00, R.drawable.three))
        jewelryList.add(Jewelry(4, "Pearl Earrings", 8000.00, R.drawable.four))
        jewelryList.add(Jewelry(5, "Pearl Earrings", 8000.00, R.drawable.five))
        jewelryList.add(Jewelry(6, "Pearl Earrings", 8000.00, R.drawable.six))

        jewelryAdapter = JewelryAdapter(jewelryList, this)
        recyclerView.adapter = jewelryAdapter

        // Call setupCartButton here
        setupCartButton()
    }

    // Move setupCartButton outside onCreate but inside the class
    private fun setupCartButton() {
        val cartButton = findViewById<ImageButton>(R.id.CartBtn)
        cartButton.setOnClickListener {
            startActivity(Intent(this, Cart::class.java))
        }


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
}