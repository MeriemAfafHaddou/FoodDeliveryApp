package com.example.fooddeliveryapp.Entity

data class Commande (
    val idClient:Int?,
    val prixTotal:Int?,
    val idCommande:Int,
    val address:String,
    val deliveryNotes:String,
    val idPerson:Int,
    val Items:List<CartItem>
)
