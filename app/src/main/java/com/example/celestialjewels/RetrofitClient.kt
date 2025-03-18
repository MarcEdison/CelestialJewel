package com.example.celestialjewels

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://192.168.100.144/celestial_jewels/MobileApp/"

    // Create an interceptor for logging network requests and responses
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Log the body of the request/response
    }

    // Create OkHttpClient with the logging interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)  // Add logging interceptor to the client
        .build()

    // Create Retrofit instance using the OkHttpClient with logging enabled
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)  // Use the custom OkHttpClient with logging
        .build()

    // Create an instance of ApiService
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
