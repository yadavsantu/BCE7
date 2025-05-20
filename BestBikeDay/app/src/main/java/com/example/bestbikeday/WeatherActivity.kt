package com.example.bestbikeday

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestbikeday.adapter.WeatherAdapter
import com.example.bestbikeday.viewmodel.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var errorText: TextView
    private lateinit var weatherRecyclerView: RecyclerView

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
        private const val OPENWEATHER_API_KEY = "YOUR_API_KEY" // Replace with your OpenWeather API key
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        setupViews()
        setupRecyclerView()
        setupLocationClient()
        checkLocationPermission()
        observeViewModel()
    }

    private fun setupViews() {
        progressBar = findViewById(R.id.progressBar)
        errorText = findViewById(R.id.errorText)
        weatherRecyclerView = findViewById(R.id.weatherRecyclerView)
    }

    private fun setupRecyclerView() {
        weatherAdapter = WeatherAdapter()
        weatherRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@WeatherActivity)
            adapter = weatherAdapter
        }
    }

    private fun setupLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                getLocation()
            }
            else -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    viewModel.fetchWeatherForecast(
                        it.latitude,
                        it.longitude,
                        OPENWEATHER_API_KEY
                    )
                }
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.weatherForecast.observe(this@WeatherActivity) { forecasts ->
                weatherAdapter.submitList(forecasts)
            }

            viewModel.isLoading.observe(this@WeatherActivity) { isLoading ->
                progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                weatherRecyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
            }

            viewModel.error.observe(this@WeatherActivity) { error ->
                errorText.text = error
                errorText.visibility = if (error != null) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation()
                } else {
                    errorText.text = "Location permission is required to show weather forecast"
                    errorText.visibility = View.VISIBLE
                }
            }
        }
    }
} 