package com.example.fooddeliveryapp.Entity

class Commande (
    val id:Int,
    val idRestaurant:Int,
    val items:List<Triple<Int, Int, Int>>,
    val address:String,
    val notes:String,
)
