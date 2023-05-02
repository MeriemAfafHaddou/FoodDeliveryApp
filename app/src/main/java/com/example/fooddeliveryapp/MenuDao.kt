package com.example.fooddeliveryapp
import androidx.room.*

@Dao
interface MenuDao {
    @Query("select * from menu where restaurant=:id_restaurant")
    fun getMenuByRestaurant(id_restaurant:Int):List<Menu>
    @Insert
    fun addMenu(vararg menu:Menu)
    @Update
    fun updateMenu(menu:Menu)
    @Delete
    fun deleteMenu(menu:Menu)
}