package com.mobile.bce7.PersonAdaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.recyclerview.widget.RecyclerView
import com.mobile.bce7.R
import com.mobile.bce7.model.Person

class PersonAdaptor(
    private val context: Context,
    private val personList: List<Person>
) : RecyclerView.Adapter<PersonAdaptor.PersonViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): PersonViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_person,parent, false
            )
        return PersonViewHolder(view)

    }

    override fun onBindViewHolder(  holder: PersonViewHolder,position: Int){
        val person=personList[position]
        holder.nameTextView.text=person.name
        holder.phoneTextView.text=person.phone

    }



    override fun getItemCount():Int=personList.size

    inner class PersonViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nameTextView: TextView=view.findViewById(R.id.nameTextView)
        val phoneTextView: TextView=view.findViewById(R.id.phoneTextView)
        val callButton: Button=view.findViewById(R.id.callButton)
        val smsButton: Button=view.findViewById(R.id.smsButton)
        val visitButton: Button=view.findViewById(R.id.websiteButton)


     }


}
