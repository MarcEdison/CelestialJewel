package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ChangePass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass)

        Log.d("ChangePass", "ChangePass Activity started")

        val passBackBtn = findViewById<Button>(R.id.passBackbtn)
        val submitBtn = findViewById<Button>(R.id.passBackbtn)

        // Back Button - Go back to Profile
        passBackBtn.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
            finish() // Close ChangePass activity
        }

        // Submit Button - Show "Password Changed!!" Popup
        submitBtn.setOnClickListener {
            showPasswordChangedDialog()
        }
    }

    // Function to show the AlertDialog and navigate to Profile
    private fun showPasswordChangedDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Success")
        builder.setMessage("Password Changed!!")
        builder.setPositiveButton("OK") { _, _ ->

            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
            finish() // Close ChangePass activity
        }
        builder.show()
    }
}
