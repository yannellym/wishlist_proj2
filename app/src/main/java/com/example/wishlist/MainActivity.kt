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
//    Ideally, an object’s properties would all be defined at the time it is created, but, because for
//    Activities and Fragments object creation is separate from view loading, the properties
//    intended to store views must start out uninitialized.
    private lateinit var submitButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemNameView: EditText
    private lateinit var itemPriceView: EditText
    private lateinit var itemLocationView: EditText

    // our mutable list of items for our wishlist
    private val wishlistItems = mutableListOf<WishlistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // finds the recyclerview, submit button, itemnNameView, itemPriceView, and itemLocationView by ID
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
        // we reference the editText, get its text, and convert it to string since its a char sequence
        val newItemName = itemNameView.text.toString()
        val newPrice = itemPriceView.text.toString()
        val newLocation = itemLocationView.text.toString()
        // create a var that holds a new object of wishlistItem with is new information of name, price, location.
        val newItem = WishlistItem(newItemName, newPrice, newLocation)
        // add this new object to our mutable list of items for our wishlist
        wishlistItems.add(newItem)
        // sets our index to be the last one in the list
        val newItemIndex = wishlistItems.size - 1
        // notifies our recycler view of what is to be inserted and what position
        recyclerView.adapter?.notifyItemInserted(newItemIndex)
            // which items are being added to our mutable list?
//        Log.d("WISHLIST", wishlistItems.toString())

        // clears the editText fields after being read
        itemNameView.setText("")
        itemPriceView.setText("")
        itemLocationView.setText("")
    }
}