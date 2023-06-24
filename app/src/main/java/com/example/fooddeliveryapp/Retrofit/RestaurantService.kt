package com.example.fooddeliveryapp.Retrofit

import com.example.fooddeliveryapp.Entity.Commande
import com.example.fooddeliveryapp.Entity.DeliveryPerson
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.Entity.Restaurant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestaurantService {
    //Restaurants
    @GET("restaurants")
    suspend fun getAllRestaurants(): Response<List<Restaurant>>

    @GET("restaurants/{id}")
    suspend fun getMenuByRestaurant(@Path("id") id: Int): Response<List<Menu>>

    @POST("order/validate")
    suspend fun validateCommand(@Body command: Commande): Response<DeliveryPerson>

    companion object {
        @Volatile
        var endpoint: RestaurantService? = null
        fun createEndpoint(): RestaurantService {
            if(endpoint ==null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl("http://192.168.42.114:4000")
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(RestaurantService::class.java)
                }
            }
            return endpoint!!

        }


    }
}