package com.example.celestialjewels

object CartManager {
    private val cartItems = mutableListOf<Jewelry>()

    fun addItem(item: Jewelry) {
        cartItems.add(item)
    }

    fun getItems(): List<Jewelry> = cartItems

    fun clearCart() {
        cartItems.clear()
    }
}