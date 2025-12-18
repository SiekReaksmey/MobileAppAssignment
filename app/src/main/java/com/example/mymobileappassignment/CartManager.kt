package com.example.mymobileappassignment

object CartManager {
    private val items = mutableListOf<CartItem>()
    private var nextId = 1

    fun addItem(name: String, price: Double, imageResId: Int, quantity: Int): CartItem {
        // Check if item already exists, if so update quantity
        val existingItem = items.find { it.name == name && it.price == price }
        if (existingItem != null) {
            existingItem.quantity += quantity
            return existingItem
        }
        
        // Otherwise add new item
        val newItem = CartItem(nextId++, name, price, imageResId, quantity)
        items.add(newItem)
        return newItem
    }

    fun getItems(): List<CartItem> = items.toList()

    fun removeItem(itemId: Int) {
        items.removeAll { it.id == itemId }
    }

    fun removeItemByNameAndPrice(name: String, price: Double) {
        items.removeAll { it.name == name && it.price == price }
    }

    fun updateItemQuantity(name: String, price: Double, newQuantity: Int) {
        val existingItem = items.find { it.name == name && it.price == price }
        if (existingItem != null) {
            if (newQuantity > 0) {
                existingItem.quantity = newQuantity
            } else {
                items.remove(existingItem)
            }
        }
    }

    fun clearCart() {
        items.clear()
    }

    fun getTotal(): Double {
        return items.sumOf { it.getTotal() }
    }

    fun getItemCount(): Int {
        return items.sumOf { it.quantity }
    }
}

