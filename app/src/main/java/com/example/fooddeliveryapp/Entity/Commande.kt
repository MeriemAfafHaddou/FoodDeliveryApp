package com.example.fooddeliveryapp.Entity

data class Commande (
    val idClient:Int?,
    val totalPrice:Int?,
    val idCommande:Int,
    val address:String,
    val deliveryNotes:String,
    val idPerson:Int,
    val Items:List<CartItem>,
    val state:String
)
