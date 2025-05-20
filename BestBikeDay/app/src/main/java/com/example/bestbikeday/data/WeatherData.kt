package com.example.bestbikeday.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val daily: List<DailyForecast>
)

@JsonClass(generateAdapter = true)
data class DailyForecast(
    val dt: Long,
    val temp: Temperature,
    val weather: List<Weather>,
    val humidity: Int,
    val wind_speed: Double
)

@JsonClass(generateAdapter = true)
data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double
)

@JsonClass(generateAdapter = true)
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) 