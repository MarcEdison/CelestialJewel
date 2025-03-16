import com.example.celestialjewels.Jewelry

object CartManager {
    private val cartItems = mutableListOf<Jewelry>()

    fun addItem(newItem: Jewelry) {
        val existingItem = cartItems.find { it.name == newItem.name }
        if (existingItem != null) {
            existingItem.quantity++ // Increase quantity if item exists
        } else {
            cartItems.add(newItem) // Otherwise, add as a new item
        }
    }

    fun getUniqueItems(): MutableList<Jewelry> = cartItems
}
