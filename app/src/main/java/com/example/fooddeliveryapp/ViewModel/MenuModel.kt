package com.example.fooddeliveryapp.ViewModel

import com.example.fooddeliveryapp.Entity.Menu
import androidx.lifecycle.ViewModel

class MenuModel: ViewModel() {
    var data= mutableListOf<Menu>()
}