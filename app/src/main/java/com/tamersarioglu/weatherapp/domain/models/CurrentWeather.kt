package com.tamersarioglu.weatherapp.domain.models

data class CurrentWeather(
    val observationTime: String,
    val temperature: Int,
    val temperatureF: Int,
    val humidity: Int,
    val windSpeed: Float,
    val windSpeedMiles: Float,
    val windDirection: String,
    val windDegree: Int,
    val feelsLike: Int,
    val feelsLikeF: Int,
    val description: String,
    val weatherCode: String,
    val weatherIconUrl: String,
    val precipitation: Float,
    val visibility: Int,
    val pressure: Int,
    val cloudCover: Int,
    val uvIndex: Int
)