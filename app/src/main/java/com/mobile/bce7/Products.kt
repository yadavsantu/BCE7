package com.mobile.bce7

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.bce7.PersonAdaptor.ProductAdapter
import com.mobile.bce7.model.Rating
import com.mobile.bce7.model.products
import com.mobile.bce7.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Products : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        recyclerView = view.findViewById(R.id.productRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        postButton=view.findViewById(R.id.postProduct)
        postButton.setOnClickListener {
            postProducts() // ✅ Only call when button is clicked
        }

        fetchProducts()


        return view
    }

    private fun postProducts(){
            val sampleProduct= products(
                id=21,
                title = "Static product",
                price = 99.99,
                description = "This is a sample static product",
                category = "Electronics",
                image = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FElephant&psig=AOvVaw0WEftPsiTZlKDwQLm5xHxl&ust=1749607037141000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCMjD0aHg5Y0DFQAAAAAdAAAAABAE",
                rating = Rating(
                    rate = 4.5,
                    count = 100
                ))

            val call= RetrofitClient.api.postProduct(sampleProduct)
            call.enqueue(object : Callback<products>{
                override fun onResponse(call: Call<products?>, response: Response<products>) {
                    if(response.isSuccessful){
                        Log.e("postedData", "Product posted successfully")
//                        Toast.makeText(this,"posted", Toast.LENGTH_SHORT).show
                        Toast.makeText(requireContext(),"Product posted successfully", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Log.e("postedData", "Something Went wrong")
                      Toast.makeText(requireContext(), "Failed:${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<products>, t: Throwable) {
                    Toast.makeText(requireContext(),"Error:${t.localizedMessage}", Toast.LENGTH_LONG).show()

                }
            })

    }


    private fun fetchProducts() {
        RetrofitClient.api.getProducts().enqueue(object : Callback<List<products>> {
            override fun onResponse(call: Call<List<products>>, response: Response<List<products>>) {
                if (response.isSuccessful) {
                    val productList = response.body() ?: emptyList()
                    recyclerView.adapter = ProductAdapter(productList)
                } else {
                    Toast.makeText(requireContext(), "FAILED TO LOAD", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<products>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}