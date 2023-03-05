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
    private lateinit var itemNameView: TextView
    private lateinit var itemPriceView: TextView
    private lateinit var itemLocationView: TextView


    private val wishlistItems = mutableListOf<WishlistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // finds the recyclerview and submit button by ID
        recyclerView = findViewById(R.id.recycler_View)
        submitButton = findViewById(R.id.submit_button)
        itemNameView = findViewById(R.id.itemNameView)
        itemPriceView = findViewById(R.id.itemPriceView)
        itemLocationView= findViewById(R.id.itemLocationView)

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
        // get the item's name, price, location
        val newItemName = findViewById<EditText>(R.id.itemNameView).text.toString()
        val newPrice = findViewById<EditText>(R.id.itemPriceView).text.toString()
        val newLocation = findViewById<EditText>(R.id.itemLocationView).text.toString()
        // create a variable that holds the three variables above
        val newItem = WishlistItem(newItemName, newPrice, newLocation)
        // add the newItem created to the wishlistitems mutable list
        wishlistItems.add(newItem)
        // notifiy the recyclerview that an item has been inserted. Insert at position size of the wishlist - 1
        val newItemIndex = wishlistItems.size - 1
        recyclerView.adapter?.notifyItemInserted(newItemIndex)
        // log to see if items have been added to the wishlistitems mutable list
        Log.d("WISHLIST", wishlistItems.toString())

        // clear the input fields
        itemNameView.text = ""
        itemPriceView.text = ""
        itemLocationView.text = ""
    }
}