package com.example.fooddeliveryapp

import androidx.room.*

@Dao
interface UserDao {
    @Query("select * from users where email=:email")
    fun getUsersByEmail(email:String):User
    @Insert
    fun addUsers(vararg users:User)
    @Update
    fun updateUser(user:User)
    @Delete
    fun deleteUser(user:User) }