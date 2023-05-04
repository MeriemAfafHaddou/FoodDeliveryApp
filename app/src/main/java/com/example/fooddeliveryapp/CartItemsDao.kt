package com.example.fooddeliveryapp
import androidx.room.*

@Dao
interface CartItemsDao {

    @Query("select * from cartItems")
    fun getCartItems():MutableList<CartItem>
    @Insert
    fun addToCart(vararg cartItem:CartItem)
    @Update
    fun updateCart(cartItem:CartItem)
    @Delete
    fun deleteCartItem(cartItem:CartItem)

}