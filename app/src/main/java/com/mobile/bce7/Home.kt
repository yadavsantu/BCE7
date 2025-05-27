package com.mobile.bce7

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val btnPersonList = view.findViewById<Button>(R.id.btnPersonList)
        val btnSignup = view.findViewById<Button>(R.id.btnSignup)

        // Open PersonActivity when Person List button is clicked
        btnPersonList.setOnClickListener {
            val intent = Intent(requireContext(), PersonActivity::class.java)
            startActivity(intent)
        }

        // Open ActivityForm when Signup button is clicked
        btnSignup.setOnClickListener {
            val intent = Intent(requireContext(), ActivityForm::class.java)
            startActivity(intent)
        }

        return view
    }
}