package com.example.fooddeliveryapp

object Cart {
    var orders= mutableListOf<CartItem>()
        private set
    fun addToCart(item: CartItem) {
        orders.add(item)
    }

    fun clear() {
        orders.clear()
    }
}

