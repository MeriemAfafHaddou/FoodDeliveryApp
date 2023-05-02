package com.example.fooddeliveryapp
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName ="cart" , foreignKeys = [
    ForeignKey(entity=Restaurant::class,
        parentColumns=["id_restau"],childColumns = ["restaurant"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE )])
data class Cart(
    @PrimaryKey
    val id_cart:Int,
    val restaurant:Int,
    val orders:MutableList<CartItem>
)
