package com.restaurantapp.user

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.restaurantapp.R
import com.restaurantapp.user.fragment.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var name: TextView
    val fragmentManager = supportFragmentManager
    var fragmentTransaction = fragmentManager.beginTransaction()
    private lateinit var title:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        title=titleToolbar
        title.text = "Menu"
        fragmentTransaction.replace(R.id.framelayoutfragment, MenuFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        auth = FirebaseAuth.getInstance()
        var navigationView :NavigationView=  findViewById(R.id.nav_view);
        var hView: View =  navigationView.getHeaderView(0);
        name=hView.findViewById(R.id.userNamefire)

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var uID:String=FirebaseAuth.getInstance().currentUser!!.uid


        val event=object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {

                var userName:String= p0.child("userName").getValue(String::class.java)!!
                name.text=userName
            }

        }
        database.getReference("/User/User/"+uID.toString()).addListenerForSingleValueEvent(event)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_menu -> {
                // Handle the camera action
                title.text = "Menu"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragment, MenuFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_reserv -> {
                title.text = "Book a table"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragment,ReservFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_orders -> {
                title.text = "Orders"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragment, OrdersFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_feedback -> {
                title.text = "FeedBack"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragment, FeedbackFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
//            R.id.nav_history -> {
//                title.text = "History"
//                val fragmentManager = supportFragmentManager
//                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.replace(R.id.framelayoutfragment, HistoryFragment())
//                fragmentTransaction.addToBackStack(null)
//                fragmentTransaction.commit()
//            }
            R.id.nav_logout -> {
                signOut()
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, SignInActivity::class.java))
        this.finish()
    }
}


