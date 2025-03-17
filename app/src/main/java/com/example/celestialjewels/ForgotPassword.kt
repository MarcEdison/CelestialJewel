package com.example.celestialjewels

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.celestialjewels.R

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val otpLayout = findViewById<ConstraintLayout>(R.id.otpLayout)

        btnSubmit.setOnClickListener {
            // Show OTP UI when Submit is clicked
            otpLayout.visibility = View.VISIBLE
            otpLayout.animate().alpha(1f).setDuration(500).start()
        }
    }
}