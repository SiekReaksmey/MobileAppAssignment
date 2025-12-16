package com.example.mymobileappassignment

data class CartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val imageResId: Int,
    var quantity: Int = 1
) {
    fun getTotal(): Double = price * quantity
}

