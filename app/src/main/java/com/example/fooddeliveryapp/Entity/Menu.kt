package com.example.fooddeliveryapp.Entity

data class Menu(
    val idMenu: Int,
    val nomMenu: String,
    val price: Int,
    val ingredients: String,
    val calories: Int,
    val rating: Double,
    val imgMenu: String,
    val restaurant:Int
)