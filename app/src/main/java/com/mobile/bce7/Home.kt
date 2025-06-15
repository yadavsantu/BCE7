package com.mobile.bce7

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.util.Locale

class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val btnPersonList = view.findViewById<Button>(R.id.btnPersonList)
        val btnSignup = view.findViewById<Button>(R.id.btnSignup)


        val btnEnglish=view.findViewById<Button>(R.id.btnEnglish)
        val btnNepali=view.findViewById<Button>(R.id.btnNepali)

        // Open PersonActivity when Person List button is clicked
        btnPersonList.setOnClickListener {
            val intent = Intent(requireContext(), PersonActivity::class.java)
            startActivity(intent)
        }

        // Open ActivityForm when Signup button is clicked
        btnSignup.setOnClickListener {
            val intent = Intent(requireContext(), ActivityForm::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }


        // translate to nepali
        btnNepali.setOnClickListener {
            setLanguage("ne")

        }
        // translate to english
        btnEnglish.setOnClickListener {
            setLanguage("en")

        }


        return view
    }

    private fun setLanguage(languageCode: String) {
        val locale = Locale(languageCode)

        val context=requireContext()
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)

        context.resources.updateConfiguration(config, context.resources.displayMetrics)

        val editor= context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", languageCode)
        editor.apply()
        requireActivity().recreate()
    }
}
