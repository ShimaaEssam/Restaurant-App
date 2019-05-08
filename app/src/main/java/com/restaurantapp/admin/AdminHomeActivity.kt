package com.restaurantapp.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import com.restaurantapp.R
import com.restaurantapp.admin.fragment.*
import kotlinx.android.synthetic.main.activity_admin_home.*
import kotlinx.android.synthetic.main.app_bar_admin_home.*

class AdminHomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val fragmentManager = supportFragmentManager
    var fragmentTransaction = fragmentManager.beginTransaction()
    private lateinit var title: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
        setSupportActionBar(toolbar)

        title=titleToolbarAdmin
        title.text = "Reservations"
        fragmentTransaction.replace(R.id.framelayoutfragmentAdmin, AdminReservFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()


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
            R.id.nav_reserv -> {
                title.text = "Reservations"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragmentAdmin, AdminReservFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_orders -> {
                title.text = "Orders"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragmentAdmin, AdminOrdersFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_reservH -> {
                title.text = "Reservations History"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragmentAdmin, RHistoryFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_ordersH -> {
                title.text = "Orders History"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragmentAdmin, OHistoryFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }

            R.id.nav_feedbackA -> {
                title.text = "FeedBack"
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framelayoutfragmentAdmin, AdminFeedbackFragment())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.nav_logout -> {
                startActivity(Intent(this, LoginAdminActivity::class.java))
                this.finish()
                val editor = getSharedPreferences("login", Context.MODE_PRIVATE).edit()
                editor.putInt("log", 0)
                editor.apply()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
