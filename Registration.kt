package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.celestial_jewels.RetrofitClient
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registration : AppCompatActivity() {

private lateinit var etusername: EditText
    private lateinit var etpassword: EditText
    private lateinit var etphoneNum: EditText
    private lateinit var etemail: EditText
    private val apiService = RetrofitClient.instance.create(ApiService::class.java)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)


        val RegisterBttn = findViewById<Button>(R.id.Registration)
         etusername =  findViewById(R.id.Etusername)
         etpassword=  findViewById(R.id.Etpass)
         etphoneNum = findViewById(R.id.EtPhoneNum)
        etemail =  findViewById(R.id.EtEmail)



        RegisterBttn.setOnClickListener(){
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            submitData()
    }
}
    private fun submitData(){
        val username = etusername.text.toString().trim()
        val password = etpassword.text.toString().trim()
        val phoneNum = etphoneNum.text.toString().trim()
        val email = etemail.text.toString().trim()


        if (username.isEmpty() || password.isEmpty() || phoneNum.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            return
        }

        val PhoneNumInt = phoneNum.toIntOrNull()
        if (PhoneNumInt == null) {
            Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show()
            return
        }

        val customers = Customers(username, password, PhoneNumInt, email)
        val json = Gson().toJson(customers)

        apiService.addCustomer(customers).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@Registration, "Customer added successfully!", Toast.LENGTH_SHORT).show()
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