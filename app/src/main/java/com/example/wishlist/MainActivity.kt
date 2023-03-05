package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var submitButton: Button
    private lateinit var recyclerView: RecyclerView

    private val wishlistItems = mutableListOf<WishlistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recycler_View)
        submitButton = findViewById(R.id.submit_button)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = RecyclerViewAdapter(wishlistItems)

        submitButton.setOnClickListener {
            addItemToWishlist()
        }

    }

    private fun addItemToWishlist() {
        val newItemName = "name of prod"
        val newPrice = "price of prod"
        val newLocation = "loc   of prod"
//        println(newItemName.toString())
        val newItem = WishlistItem(newItemName, newPrice, newLocation)
        wishlistItems.add(newItem)
        println(wishlistItems)
        recyclerView.adapter?.notifyItemInserted(wishlistItems.size - 1)
    }

}