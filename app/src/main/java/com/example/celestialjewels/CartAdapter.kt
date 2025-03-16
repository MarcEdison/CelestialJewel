package com.example.celestialjewels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val cartItems: MutableList<Jewelry>, // Ensure we can modify quantity
    private val updateTotal: () -> Unit // Callback to update total price
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.jewelryImage)
        val nameText: TextView = itemView.findViewById(R.id.jewelryName)
        val priceText: TextView = itemView.findViewById(R.id.jewelryPrice)
        val quantityText: TextView = itemView.findViewById(R.id.itemQuantity)
        val btnIncrease: ImageButton = itemView.findViewById(R.id.btnIncrease)
        val btnDecrease: ImageButton = itemView.findViewById(R.id.btnDecrease)
        val btnRemove: ImageButton = itemView.findViewById(R.id.btnRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.nameText.text = currentItem.name
        holder.priceText.text = "₱${String.format("%.2f", currentItem.price)}"
        holder.quantityText.text = currentItem.quantity.toString()

        // Increase quantity
        holder.btnIncrease.setOnClickListener {
            currentItem.quantity++
            holder.quantityText.text = currentItem.quantity.toString()
            updateTotal() // Update total when quantity changes
        }

        // Decrease quantity
        holder.btnDecrease.setOnClickListener {
            if (currentItem.quantity > 1) {
                currentItem.quantity--
                holder.quantityText.text = currentItem.quantity.toString()
                updateTotal()
            }
        }

        // Remove item from cart
        holder.btnRemove.setOnClickListener {
            cartItems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)
            updateTotal()
        }
    }

    override fun getItemCount() = cartItems.size
}
