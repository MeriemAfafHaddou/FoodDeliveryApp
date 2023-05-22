package com.example.fooddeliveryapp.Retrofit

import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.Entity.Restaurant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RestaurantService {
    //Restaurants
    @GET("restaurants")
    suspend fun getAllRestaurants(): Response<List<Restaurant>>
    companion object {
        private var endpoint: RestaurantService? = null
        fun createEndpoint(): RestaurantService {
            if (endpoint != null) {
                return endpoint!!
            } else {
                endpoint = Retrofit.Builder()
                    .baseUrl("https://be26-105-235-130-112.ngrok-free.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RestaurantService::class.java)
                return endpoint!!
            }
        }



    }
}