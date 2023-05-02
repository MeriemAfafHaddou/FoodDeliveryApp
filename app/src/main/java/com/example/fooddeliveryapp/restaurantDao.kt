package com.example.fooddeliveryapp

import androidx.room.*

@Dao
interface RestaurantDao {
    @Query("select * from restaurants where name=:name")
    fun getRestaurantsByName(name:String):Restaurant
    @Insert
    fun addRestaurant(vararg restaurant:Restaurant)
    @Update
    fun updateRestaurant(restaurant:Restaurant)
    @Delete
    fun deleteRestaurant(restaurant:Restaurant)
}