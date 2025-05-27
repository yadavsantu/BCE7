package com.mobile.bce7

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class About : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        val btnLinkedIn = view.findViewById<ImageButton>(R.id.btnLinkedIn)
        val btnFacebook = view.findViewById<ImageButton>(R.id.btnFacebook)
        val btnVisitWebsite = view.findViewById<Button>(R.id.btnVisitWebsite)

        btnLinkedIn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/santu-yadav-77b5b6138/"))
            startActivity(intent)
        }

        btnFacebook.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/iamersantu/"))
            startActivity(intent)
        }

        btnVisitWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://santuyadav.com.np"))
            startActivity(intent)
        }

        return view
    }
}