package com.mobile.bce7

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        val sharedPreferences= newBase?.getSharedPreferences("Settings", Context.MODE_PRIVATE)

        val lang= sharedPreferences?.getString("My_Lang", "en")?:"en"
        val locale = Locale(lang)
        val config = newBase?.resources?.configuration
        config?.setLocale((locale))
        val context=newBase?.createConfigurationContext(config!!)
        super.attachBaseContext(context)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerlayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)

        val toogle= ActionBarDrawerToggle(
            this,drawerlayout,toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerlayout.addDrawerListener(toogle)
        toogle.syncState()
        //default fragment
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,Home())
                .commit()
            navigationView.setCheckedItem(R.id.nav_home)

        }
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.nav_home ->{
                   supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, Home()).commit()
                }
                R.id.nav_products->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, Products()).commit()
                }

                R.id.nav_about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, About()).commit()
                }
                R.id.nav_contact -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, Contact()).commit()
                }
                R.id.nav_logout ->{
                    Toast.makeText(this,"Logged out successfully!", Toast.LENGTH_LONG).show()
                }

            }
            drawerlayout.closeDrawer(GravityCompat.START)
            true
        }

}

}