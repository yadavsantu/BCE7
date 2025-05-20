package com.example.helloworld

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextText: EditText
    private lateinit var button: Button
    private lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextText = findViewById(R.id.editTextText)
        button = findViewById(R.id.button)
        calendarView = findViewById(R.id.calendarView)

        button.setOnClickListener {
            val username = editTextText.text.toString()
            Toast.makeText(this, "Submitted: $username", Toast.LENGTH_SHORT).show()
        }
    }
}