package com.example.fooddeliveryapp.Dao
import androidx.room.*
import com.example.fooddeliveryapp.Entity.CartItem

@Dao
interface CartItemsDao {

    @Query("select * from cartItems")
    fun getCartItems():MutableList<CartItem>
    @Insert
    fun addToCart(vararg cartItem: CartItem)
    @Update
    fun updateCart(cartItem: CartItem)
    @Delete
    fun deleteCartItem(cartItem: CartItem)

}