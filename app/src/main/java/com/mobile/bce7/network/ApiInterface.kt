package com.mobile.bce7.network

import com.mobile.bce7.model.products
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("products")
    fun getProducts(): Call<List<products>>
    @POST("products")
    fun postProduct(@Body product: products): Call<products>


}