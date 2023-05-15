package com.example.fooddeliveryapp.Entity
data class User(
    var userId:Long?,
    var fullName:String?,
    var email:String?,
    var pwd:String?,
    var phoneNum:String,
    var city:String?,
    var address: String?
) {
}