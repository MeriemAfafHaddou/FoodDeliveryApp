package com.example.fooddeliveryapp
import androidx.room.*

@Dao
interface CartDao {
    @Query("select restaurant from cart where restaurant=:id ")
    fun getRestaurantId(id:Int):Restaurant
    @Insert
    fun addItemToCart(vararg cartItem:Cart)
    @Update
    fun updateCart(cartItem:Cart)
    @Delete
    fun deleteCart(cartItem:Cart)
}