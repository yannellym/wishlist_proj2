package com.example.wishlist

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val wishlistItems: MutableList<WishlistItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.itemNameView)
        val itemPriceTextView: TextView  = itemView.findViewById(R.id.itemPriceView)
        val itemLocationTextView: TextView  = itemView.findViewById(R.id.itemLocationView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val itemView = inflater.inflate(R.layout.wishlist_item, parent, false)
        // Return a new holder instance
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // gets the current item from the wishlistitems mutable list
        val item = wishlistItems[position]
        // Set item views based on views and data model
        holder.itemNameTextView.text = item.name
        holder.itemPriceTextView.text = item.price
        holder.itemLocationTextView.text = item.location

        // adds a long click listener to the items above.
        // When the user does a long click, it removes it for them
        // it notifies the recycler view of the item removed
        holder.itemView.setOnLongClickListener {
            wishlistItems.removeAt(position)
            notifyItemRemoved(position)
            true
        }

        // Add a click listener to the item location text view
        holder.itemLocationTextView.setOnClickListener {
            // Create an intent to view the item location link
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.location)
            // Launch the intent
            holder.itemView.context.startActivity(intent)
        }
    }
    // func to get the size of the wishlistitems mutable list
    override fun getItemCount() = wishlistItems.size

}
