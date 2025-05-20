package com.example.bestbikeday.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bestbikeday.R
import com.example.bestbikeday.data.DailyForecast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private var weatherList: List<DailyForecast> = emptyList()

    fun submitList(newList: List<DailyForecast>) {
        weatherList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int = weatherList.size

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateText: TextView = itemView.findViewById(R.id.dateText)
        private val descriptionText: TextView = itemView.findViewById(R.id.descriptionText)
        private val humidityText: TextView = itemView.findViewById(R.id.humidityText)
        private val windText: TextView = itemView.findViewById(R.id.windText)
        private val temperatureText: TextView = itemView.findViewById(R.id.temperatureText)
        private val tempRangeText: TextView = itemView.findViewById(R.id.tempRangeText)

        fun bind(forecast: DailyForecast) {
            val date = Date(forecast.dt * 1000)
            val dateFormat = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
            dateText.text = dateFormat.format(date)

            descriptionText.text = forecast.weather.firstOrNull()?.description?.capitalize()
            humidityText.text = "Humidity: ${forecast.humidity}%"
            windText.text = "Wind: ${forecast.wind_speed} m/s"
            temperatureText.text = "${forecast.temp.day}°C"
            tempRangeText.text = "H:${forecast.temp.max}° L:${forecast.temp.min}°"
        }
    }
} 