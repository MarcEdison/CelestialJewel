package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.celestialjewels.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val changePassTextView = findViewById<TextView>(R.id.changepass)
        val logoutButton = findViewById<TextView>(R.id.logoutButton) // Find logout button

        // Set Profile as selected when the activity opens
        bottomNavigationView.selectedItemId = R.id.action_profile

        // Navigate to Change Password screen
        changePassTextView.setOnClickListener {
            val intent = Intent(this, ChangePass::class.java)
            startActivity(intent)
        }

        // Handle Logout Button Click
        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish() // Close the Profile activity
        }

        // Handle Bottom Navigation
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
                    // Already in Profile, return true but do nothing
                    true
                }
                else -> false
            }
        }

    }
}
