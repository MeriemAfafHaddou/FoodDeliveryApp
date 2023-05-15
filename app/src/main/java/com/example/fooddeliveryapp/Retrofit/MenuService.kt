package com.example.fooddeliveryapp.Retrofit

import com.example.fooddeliveryapp.Entity.Menu
import com.example.fooddeliveryapp.Entity.Restaurant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MenuService {
    //Menus
    @GET("restaurant/{id}/menu")
    suspend fun getMenuByRestaurant(): Response<List<Menu>>


    companion object {
        @Volatile
        var endpoint: MenuService? = null
        fun createEndpoint(): MenuService {
            if(endpoint ==null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl("")
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(MenuService::class.java)
                }
            }
            return endpoint!!

        }


    }
}