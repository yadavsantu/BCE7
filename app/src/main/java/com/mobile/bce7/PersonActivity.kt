package com.mobile.bce7

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.bce7.PersonAdaptor.PersonAdaptor
import com.mobile.bce7.model.Person

class PersonActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var personAdapter: PersonAdaptor

    val personList=listOf(
        Person("Santu Yadav", "+977-9813768889", "https://santuyadav.com.np"),
        Person("Saurab Ghimire", "+977-9813768889", "https://santuyadav.com.np"),
        Person("Gopal Mahato", "+977-9813768889", "https://santuyadav.com.np")
        )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_person)


        //set up toolbar as action bar
        val toolbar: androidx.appcompat.widget.Toolbar= findViewById(R.id.toolBar)
        supportActionBar?.apply {
            title="Person List"
            setDisplayHomeAsUpEnabled(true)// show back btn
        }
        // handle back btn click
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()// navigate back
        }
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(this)
        personAdapter= PersonAdaptor(this, personList)
        recyclerView.adapter=personAdapter

    }
}