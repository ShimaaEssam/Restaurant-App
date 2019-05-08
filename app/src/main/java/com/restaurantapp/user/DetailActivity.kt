package com.restaurantapp.user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.webkit.ValueCallback
import com.giants.giantsgym.firebaseHelper.FirebaseReader
import com.google.firebase.database.FirebaseDatabase
import com.restaurantapp.Adapter.foodAdapter
import com.restaurantapp.Model.Food
import com.restaurantapp.R
import java.util.ArrayList

class DetailActivity : AppCompatActivity() {
    lateinit var database: FirebaseDatabase
    //lateinit var service: DatabaseReference
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
   lateinit var  id:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        recyclerView = findViewById(R.id.recycler_detail)
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        if (intent.hasExtra("ID")) {
            id = intent.extras!!.getString("ID")
        }
        loadMenu()
    }

    private fun loadMenu() {

        FirebaseReader.getFireDataFiliterdList(Food::class.java, ValueCallback { objects ->
            var list = objects as ArrayList<*> as ArrayList<Food>
            val adapter = foodAdapter(list, this)
            recyclerView.adapter = adapter

        }, id)

    }
}