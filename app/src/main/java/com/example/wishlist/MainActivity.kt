package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var submitButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemNameView: EditText
    private lateinit var itemPriceView: EditText
    private lateinit var itemLocationView: EditText


    private val wishlistItems = mutableListOf<WishlistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // finds the recyclerview and submit button by ID
        recyclerView = findViewById(R.id.recycler_View)
        submitButton = findViewById(R.id.submit_button)
        itemNameView = findViewById(R.id.itemNameView)
        itemPriceView = findViewById(R.id.itemPriceView)
        itemLocationView = findViewById(R.id.itemLocationView)

        // Sets the recyclerView layout manager to this
        recyclerView.layoutManager = LinearLayoutManager(this)
        // sets the recyclerview's adapter
        recyclerView.adapter = RecyclerViewAdapter(wishlistItems)
        // sets a click listener on the submit button
        submitButton.setOnClickListener {
            // calls the func to add the item to the wish list
            addItemToWishlist()
        }
    }

    // func to add items to the list
    private fun addItemToWishlist() {
        val newItemName = itemNameView.text.toString()
        val newPrice = itemPriceView.text.toString()
        val newLocation = itemLocationView.text.toString()
        val newItem = WishlistItem(newItemName, newPrice, newLocation)

        wishlistItems.add(newItem)
        val newItemIndex = wishlistItems.size - 1
        recyclerView.adapter?.notifyItemInserted(newItemIndex)

        Log.d("WISHLIST", wishlistItems.toString())

        itemNameView.setText("")
        itemPriceView.setText("")
        itemLocationView.setText("")
    }
}