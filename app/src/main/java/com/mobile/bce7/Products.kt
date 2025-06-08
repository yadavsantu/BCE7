package com.mobile.bce7

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.bce7.PersonAdaptor.ProductAdapter
import com.mobile.bce7.model.products
import com.mobile.bce7.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Products : Fragment() {

    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        recyclerView = view.findViewById(R.id.productRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fetchProducts()

        return view
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