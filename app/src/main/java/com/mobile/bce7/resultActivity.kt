package com.mobile.bce7

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class resultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        // Retrieve data from intent
        val name = intent.getStringExtra("name")
        val address = intent.getStringExtra("address")
        val email = intent.getStringExtra("email")
        val gender = intent.getStringExtra("gender")
        val country = intent.getStringExtra("country")
        val terms = intent.getStringExtra("terms")


        // Set text on each individual TextView
        findViewById<TextView>(R.id.tvResultName).text = "Name: $name"
        findViewById<TextView>(R.id.tvResultAddress).text = "Address: $address"
        findViewById<TextView>(R.id.tvResultEmail).text = "Email: $email"
        findViewById<TextView>(R.id.tvResultGender).text = "Gender: $gender"
        findViewById<TextView>(R.id.tvResultCountry).text = "Country: $country"
        findViewById<TextView>(R.id.tvResultTerms).text = "Terms Accepted: $terms"
    }
}