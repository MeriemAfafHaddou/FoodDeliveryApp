package com.example.fooddeliveryapp.Retrofit

import com.example.fooddeliveryapp.Entity.Commande
import com.example.fooddeliveryapp.Entity.DeliveryPerson
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

    @POST("order/validate")
    suspend fun validateCommand(@Body command: Commande): Response<DeliveryPerson>

    @GET("restaurants/{id}/ratings")
    suspend fun getReviewsByRestaurant(@Path("id") id: Int): Response<List<Review>>

    @POST("restaurants/{id}/ratings")
    suspend fun rateRestaurant(@Path("id") id: Int, @Body review: Review)

    companion object {
        private var endpoint: RestaurantService? = null
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