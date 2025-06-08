package com.mobile.bce7.network

import retrofit2.create

object RetrofitClient {

    private const val BASE_URL="https://fakestoreapi.com/"
    val api: ApiInterface by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    }
}