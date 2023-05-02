package com.example.fooddeliveryapp
import android.os.RecoverySystem
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName ="menu", foreignKeys = [
    ForeignKey(entity=Restaurant::class,
        parentColumns=["id_restau"],childColumns = ["restaurant"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE )])
data class Menu(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val ingredients: String,
    val cal: Int,
    val rating: Double,
    val img: Int,
    val restaurant:Int
)
