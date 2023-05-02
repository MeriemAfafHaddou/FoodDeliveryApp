package com.example.fooddeliveryapp
import androidx.room.*

@Dao
interface MenuDao {
    @Query("select * from menu where id_restaurant=:id_restaurant")
    fun getMenuByRestaurant(id_restaurant:Int):Menu
    @Insert
    fun addMenu(vararg menu:Menu)
    @Update
    fun updateMenu(menu:Menu)
    @Delete
    fun deleteMenu(menu:Menu)
}