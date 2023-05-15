package com.example.fooddeliveryapp.Retrofit

import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.Entity.Restaurant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RestaurantService {
    //Restaurants
    @GET("restaurant/getall")
    suspend fun getAllRestaurants(): Response<List<Restaurant>>

    companion object {
        @Volatile
        var endpoint: RestaurantService? = null
        fun createEndpoint(): RestaurantService {
            if(endpoint ==null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl("")
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(RestaurantService::class.java)
                }
            }
            return endpoint!!

        }


    }
}