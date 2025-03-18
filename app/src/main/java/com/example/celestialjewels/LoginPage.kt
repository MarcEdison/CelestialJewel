package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPage : AppCompatActivity() {
    private val apiService = RetrofitClient.apiService  // Use the correct reference for apiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameField = findViewById<EditText>(R.id.Etusername)
        val passwordField = findViewById<EditText>(R.id.Etpass)
        val loginBttn = findViewById<Button>(R.id.Login)
        val forgotPasswordBttn = findViewById<TextView>(R.id.fgtpass)
        val registerBttn = findViewById<TextView>(R.id.Register)


        // Handle login button click
        loginBttn.setOnClickListener {
            val username = usernameField.text.toString()
            val password = passwordField.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a Customers object to send to the PHP script
            val loginData = Customers(username, password, 0, "") // Initialize with dummy values for phoneNum and email

            // Make the login request using Retrofit
            apiService.login(loginData).enqueue(object : Callback<Customers> {  // Use apiService to make the login call
                override fun onResponse(call: Call<Customers>, response: Response<Customers>) {
                    if (response.isSuccessful) {
                        val customer = response.body()
                        if (customer != null) {
                            // Login success, navigate to HomePage
                            val intent = Intent(this@LoginPage, HomePage::class.java)
                            startActivity(intent)
                        } else {
                            // Login failed, show error message
                            Toast.makeText(this@LoginPage, "Invalid username or password", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Server error
                        Toast.makeText(this@LoginPage, "Server error. Please try again later.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Customers>, t: Throwable) {
                    // Error with network request
                    Toast.makeText(this@LoginPage, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        // Navigate to Forgot Password page
        forgotPasswordBttn.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Navigate to Registration page
        registerBttn.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
    }
}
