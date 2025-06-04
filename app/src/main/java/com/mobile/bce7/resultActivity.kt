package com.mobile.bce7

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import database.MyDatabaseHelper

class resultActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val dbHelper= MyDatabaseHelper(this)
        val cursor= dbHelper.getAllUserData()
        val userList=mutableListOf<String>()
        if (cursor.moveToFirst()){
            do{
                val name= cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val address= cursor.getString(cursor.getColumnIndexOrThrow("address"))
                val email= cursor.getString(cursor.getColumnIndexOrThrow("email"))
                val gender= cursor.getString(cursor.getColumnIndexOrThrow("gender"))
                val country= cursor.getString(cursor.getColumnIndexOrThrow("country"))
                val terms= cursor.getString(cursor.getColumnIndexOrThrow("terms"))
                userList.add("Name: $name, Address: $address, Email: $email, Gender: $gender, Country: $country, Terms: $terms ")

            }while(cursor.moveToNext())

        }
        cursor.close()
        // se up a listview or recycler to show userlist
        val listview= findViewById<ListView>(R.id.listViewUsers)
        val adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1, userList)
        listview.adapter= adapter

    }
}