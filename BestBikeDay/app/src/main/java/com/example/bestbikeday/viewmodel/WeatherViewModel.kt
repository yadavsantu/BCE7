package com.example.bestbikeday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestbikeday.data.DailyForecast
import com.example.bestbikeday.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weatherForecast = MutableLiveData<List<DailyForecast>>()
    val weatherForecast: LiveData<List<DailyForecast>> = _weatherForecast

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchWeatherForecast(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getWeatherForecast(latitude, longitude, apiKey)
                _weatherForecast.value = response.daily
            } catch (e: Exception) {
                _error.value = "Error fetching weather data: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
} 