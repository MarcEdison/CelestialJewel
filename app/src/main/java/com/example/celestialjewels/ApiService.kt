package com.example.celestialjewels


import retrofit2.Call
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")

    @POST("create.php")  // Make sure this path is correct!
    fun addCustomer(@Body customer: Customers): Call<ResponseBody>

    @POST("login.php")  // Replace with your actual server URL
    fun login(@Body loginData: Customers): Call<Customers>
}
