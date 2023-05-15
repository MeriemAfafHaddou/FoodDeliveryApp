package com.example.fooddeliveryapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.Entity.Restaurant

class RestaurantModel: ViewModel() {
    var data= mutableListOf<Restaurant>()
}