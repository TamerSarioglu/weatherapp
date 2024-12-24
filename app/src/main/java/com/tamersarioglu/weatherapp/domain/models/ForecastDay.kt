package com.tamersarioglu.weatherapp.domain.models

data class ForecastDay(
    val date: String,
    val maxTemp: Int,
    val minTemp: Int,
    val avgTemp: Int,
    val totalSnow: Float,
    val sunHours: Float,
    val uvIndex: Int,
    val astronomy: Astronomy,
    val hourlyForecasts: List<HourlyForecast>
)