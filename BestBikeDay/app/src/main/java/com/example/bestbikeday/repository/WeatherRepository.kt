package com.example.bestbikeday.repository

import com.example.bestbikeday.api.WeatherApiService
import com.example.bestbikeday.data.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val apiService: WeatherApiService) {
    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeatherResponse = withContext(Dispatchers.IO) {
        apiService.getWeatherForecast(latitude, longitude, apiKey = apiKey)
    }
} 