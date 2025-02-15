package com.example.celestialjewels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.celestialjewels.R

class CartAdapter(private val cartItems: List<Jewelry>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.jewelryImage)
        val nameText: TextView = itemView.findViewById(R.id.jewelryName)
        val priceText: TextView = itemView.findViewById(R.id.jewelryPrice)
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
    }

    override fun getItemCount() = cartItems.size
}