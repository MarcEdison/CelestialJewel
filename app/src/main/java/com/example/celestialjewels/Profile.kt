package com.example.celestialjewels

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val changePassTextView = findViewById<TextView>(R.id.changedetails)
        val logoutButton = findViewById<TextView>(R.id.logoutButton) // Logout Button
        val shopLocationButton = findViewById<TextView>(R.id.addressButton) // Shop Location Button

        // Set Profile as selected when the activity opens
        bottomNavigationView.selectedItemId = R.id.action_profile

        val btnDelivery = findViewById<ImageButton>(R.id.btn_delivery)
        btnDelivery.setOnClickListener {
            val intent = Intent(this, activity_toclaim::class.java)
            startActivity(intent)
        }


        // Navigate to Change Password screen
        changePassTextView.setOnClickListener {
            val intent = Intent(this, ChangePass::class.java)
            startActivity(intent)
        }

        // Navigate to Shop Location screen
        shopLocationButton.setOnClickListener {
            val intent = Intent(this, ShopLocation::class.java)
            startActivity(intent)
        }

        // Handle Logout Button Click with Confirmation Popup
        logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
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
                R.id.action_profile -> true // Already in Profile
                else -> false
            }
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Logout")
        builder.setMessage("Are you sure you want to log out?")

        builder.setPositiveButton("Yes") { _, _ ->
            // If "Yes" is pressed, navigate to the Login Page
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            // If "Cancel" is pressed, dismiss the dialog
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}
