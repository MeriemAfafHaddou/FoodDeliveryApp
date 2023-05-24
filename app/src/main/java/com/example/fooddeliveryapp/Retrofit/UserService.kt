package com.example.fooddeliveryapp.Retrofit

import androidx.databinding.Bindable
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.Entity.Client
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST("users/login")
    suspend fun login(@Body email:String, @Body pwd:String):Response<Client>

    @GET("users/{id}")
    suspend fun getUserInfos(@Path("id") id: Int): Response<Client>
    companion object {
        @Volatile
        var endpoint: UserService? = null
        fun createEndpoint(): UserService {
            if(endpoint ==null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl("https://food-delivery-service.onrender.com")
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(UserService::class.java)
                }
            }
            return endpoint!!
        }
    }
}