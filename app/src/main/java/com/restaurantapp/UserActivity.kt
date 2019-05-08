package com.restaurantapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.restaurantapp.admin.LoginAdminActivity
import com.restaurantapp.user.SignInActivity
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    private lateinit var userBtn:Button
    private lateinit var adminBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        userBtn=user
        adminBtn=admin
        userBtn.setOnClickListener {
            var intent = Intent(this@UserActivity, SignInActivity::class.java)
                startActivity(intent)
                finish()
        }
        adminBtn.setOnClickListener {
            var intent = Intent(this@UserActivity, LoginAdminActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
