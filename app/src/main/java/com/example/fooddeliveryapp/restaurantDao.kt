package com.example.fooddeliveryapp

import androidx.room.*

@Dao
interface RestaurantDao {
    @Query("select * from restaurants")
    fun getRestaurants():List<Restaurant>
    @Insert
    fun addRestaurant(vararg restaurant:Restaurant)
    @Update
    fun updateRestaurant(restaurant:Restaurant)
    @Delete
    fun deleteRestaurant(restaurant:Restaurant)
}