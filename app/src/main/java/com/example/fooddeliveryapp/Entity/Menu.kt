package com.example.fooddeliveryapp.Entity
data class Menu(
    val id: Int,
    val name: String,
    val price: Int,
    val ingredients: String,
    val cal: Int,
    val rating: Double,
    val img: String,
    val restaurant:Int
)
