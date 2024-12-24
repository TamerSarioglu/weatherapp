package com.tamersarioglu.weatherapp.domain.repository

import com.tamersarioglu.weatherapp.domain.models.WeatherInfo

interface WeatherRepository {
    suspend fun getWeather(location: String): Result<WeatherInfo>
}