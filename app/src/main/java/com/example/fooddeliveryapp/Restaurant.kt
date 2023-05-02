package com.example.fooddeliveryapp
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="restaurants" )
data class Restaurant(
    @PrimaryKey
    val id_restau: Int,
    val name: String,
    val type: String,
    val img: Int,
    val address: String,
    val map: String,
    val mapweb: String,
    val rating: Double,
    val fb: String,
    val fbweb: String,
    val ig: String,
    val igweb: String
)