package com.example.fooddeliveryapp
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="users" )
data class User(
    @PrimaryKey
    var userId:Long?,
    var fullName:String?,
    var email:String?,
    var pwd:String?,
    var phoneNum:String,
    var city:String?,
    var address: String?
)