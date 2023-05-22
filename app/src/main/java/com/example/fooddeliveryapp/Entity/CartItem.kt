package com.example.fooddeliveryapp.Entity
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName ="cartItems")
data class CartItem (
    @PrimaryKey
    val id:Int,
    val restaurant_id:Int,
    val name: String,
    val unitPrice: Int,
    val ingredients: String,
    val cal: Int,
    val rating: Double,
    val img: String,
    val size:String,
    var quantity:Int,
    var total:Int
)

