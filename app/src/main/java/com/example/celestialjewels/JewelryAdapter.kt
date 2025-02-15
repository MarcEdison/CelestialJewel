package com.example.celestialjewels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.celestialjewels.R

class JewelryAdapter(
    private val jewelryList: List<Jewelry>,
    private val context: Context
) : RecyclerView.Adapter<JewelryAdapter.JewelryViewHolder>() {

    inner class JewelryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.jewelryImage)
        val nameText: TextView = itemView.findViewById(R.id.jewelryName)
        val priceText: TextView = itemView.findViewById(R.id.jewelryPrice)
        val addToCartButton: ImageButton = itemView.findViewById(R.id.CartBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JewelryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.jewelry_card, parent, false)
        return JewelryViewHolder(view)
    }

    override fun onBindViewHolder(holder: JewelryViewHolder, position: Int) {
        val currentItem = jewelryList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.nameText.text = currentItem.name
        holder.priceText.text = "₱${String.format("%.2f", currentItem.price)}"

        holder.addToCartButton.setOnClickListener {
            CartManager.addItem(currentItem)
            Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = jewelryList.size
}