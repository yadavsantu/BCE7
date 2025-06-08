package com.mobile.bce7.model

data class products(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating,
)

data class Rating(
    val rate: Double,
    val count: Long,
)
