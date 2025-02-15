package com.example.celestialjewels

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.celestialjewels.R

class LoginPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var LoginBttn = findViewById<Button>(R.id.Login)




        LoginBttn.setOnClickListener(){
           val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        val registerBttn = findViewById<TextView>(R.id.Register)
        registerBttn.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }



    }
}