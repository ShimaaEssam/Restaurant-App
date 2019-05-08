package com.restaurantapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent = Intent(this@MainActivity, UserActivity::class.java)

        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 5000)
    }
}
