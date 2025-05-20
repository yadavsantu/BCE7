package com.mobile.bce7

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class resultActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        val name=intent.getStringExtra("name")
        val address=intent.getStringExtra("address")
        val email =intent.getStringExtra("email")
        val gender =intent.getStringExtra("gender")
        val country =intent.getStringExtra("country")
        val terms =intent.getStringExtra("terms")
        val txtResult=findViewById<TextView>(R.id.txtResult)
        txtResult.text= """ 
            Name:$name
            Address:$address
            Gender:$gender
            Country:$country
            Accepted Terms:$terms
            """.trimIndent()
    }
}