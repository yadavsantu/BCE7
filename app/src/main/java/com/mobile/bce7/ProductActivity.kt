package com.mobile.bce7

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.bce7.PersonAdaptor.ProductAdapter
import com.mobile.bce7.model.products
import com.mobile.bce7.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)

        recyclerView = findViewById(R.id.productRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchProducts()
    }

    private fun fetchProducts() {
        RetrofitClient.api.getProducts().enqueue(object : Callback<List<products>> {
            override fun onResponse(call: Call<List<products>>, response: Response<List<products>>) {
                if (response.isSuccessful) {
                    val productList = response.body() ?: emptyList()
                    recyclerView.adapter = ProductAdapter(productList)
                } else {
                    Toast.makeText(this@ProductActivity, "FAILED TO LOAD", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<products>>, t: Throwable) {
                Toast.makeText(this@ProductActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}