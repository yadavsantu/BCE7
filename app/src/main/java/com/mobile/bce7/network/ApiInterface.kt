package com.mobile.bce7.network

import com.mobile.bce7.model.products
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    fun getProducts(): Call<List<products>>


}