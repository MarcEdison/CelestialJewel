package com.example.celestialjewels


import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("create.php")
    fun addCustomer(@Body customers: Customers): Call<ResponseBody>



}
