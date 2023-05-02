package com.example.fooddeliveryapp

data class CartItem (
    val id:Int,
    val order:Menu,
    val size:String,
    var quantity:Int,
    var total:Int
)

