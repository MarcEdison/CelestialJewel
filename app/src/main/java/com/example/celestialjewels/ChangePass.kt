package com.example.celestialjewels

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity



class ChangePass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass)

        Log.d("ChangePass", "ChangePass Activity started")
    }
}