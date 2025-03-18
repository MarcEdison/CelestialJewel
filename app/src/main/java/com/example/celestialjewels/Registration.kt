package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registration : AppCompatActivity() {

    private lateinit var etusername: EditText
    private lateinit var etpassword: EditText
    private lateinit var etconpassword: EditText
    private lateinit var etphoneNum: EditText
    private lateinit var etemail: EditText
    private lateinit var backButton: MaterialButton
    private val apiService = RetrofitClient.instance.create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Initialize UI elements
        etusername = findViewById(R.id.Etusername)
        etpassword = findViewById(R.id.Etpass)
        etconpassword = findViewById(R.id.Etconpass)
        etphoneNum = findViewById(R.id.EtPhoneNum)
        etemail = findViewById(R.id.EtEmail)
        backButton = findViewById(R.id.btnbacktologin)
        val registerBttn = findViewById<Button>(R.id.Registration)

        // Back button functionality
        backButton.setOnClickListener {
            finish() // Closes Registration and returns to LoginPage
        }

        // Register button functionality
        registerBttn.setOnClickListener {
            submitData()
        }
    }

    private fun submitData() {
        val username = etusername.text.toString().trim()
        val password = etpassword.text.toString().trim()
        val conpassword = etconpassword.text.toString().trim()
        val phoneNum = etphoneNum.text.toString().trim()
        val email = etemail.text.toString().trim()

        // Validate inputs
        if (username.isEmpty() || password.isEmpty() || conpassword.isEmpty() || phoneNum.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != conpassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show()
            return
        }

        val phoneNumLong = phoneNum.toLongOrNull()
        if (phoneNumLong == null || phoneNum.length < 10) {
            Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show()
            return
        }

        val customers = Customers(username, password, phoneNumLong.toInt(), email)
        val json = Gson().toJson(customers)

        apiService.addCustomer(customers).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@Registration, "Customer added successfully!", Toast.LENGTH_SHORT).show()

                    // Navigate to login page AFTER successful registration
                    val intent = Intent(this@Registration, LoginPage::class.java)
                    startActivity(intent)
                    finish() // Close registration activity
                } else {
                    Log.e("API_ERROR", "Error Code: ${response.code()} - ${response.message()}")
                    Toast.makeText(this@Registration, "Server Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("API_ERROR", "Network Failure: ${t.message}")
                Toast.makeText(this@Registration, "Failed to connect to server", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
