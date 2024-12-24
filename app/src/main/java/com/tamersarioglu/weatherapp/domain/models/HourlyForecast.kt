package com.tamersarioglu.weatherapp.domain.models

import java.time.LocalTime

data class HourlyForecast(
    val time: LocalTime,
    val temperature: Int,
    val temperatureF: Int,
    val windSpeed: Float,
    val description: String,
    val weatherCode: String,
    val weatherIconUrl: String,
    val precipitation: Float,
    val humidity: Int,
    val visibility: Int,
    val pressure: Int,
    val cloudCover: Int,
    val feelsLike: Int,
    val chanceOfRain: Int
)