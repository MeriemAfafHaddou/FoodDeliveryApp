package com.example.fooddeliveryapp.Retrofit

import com.example.fooddeliveryapp.Entity.Commande
import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.Entity.Restaurant
import com.example.fooddeliveryapp.Entity.Review
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

    @GET("restaurants/menu/{id}")
    suspend fun getMenuDetails(@Path("id") id: Int): Response<Menu>
    @GET("restaurants/{id}/ratings")
    suspend fun getReviewsByRestaurant(@Path("id") id: Int): Response<List<Review>>
    @POST("restaurants/{id}/ratings")
    suspend fun rateRestaurant(@Path("id") id: Int, @Body review: Review)

    @POST("")
    suspend fun validateCommand(@Body command: Commande)

    companion object {
        @Volatile
        var endpoint: RestaurantService? = null
        fun createEndpoint(): RestaurantService {
            if(endpoint ==null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl("https://instant-delivery.onrender.com")
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(RestaurantService::class.java)
                }
            }
            return endpoint!!

        }


    }
}