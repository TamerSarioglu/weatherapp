package com.tamersarioglu.weatherapp.presentation

import com.tamersarioglu.weatherapp.domain.models.CurrentWeather
import com.tamersarioglu.weatherapp.domain.models.ForecastDay
import com.tamersarioglu.weatherapp.domain.models.HourlyForecast

data class WeatherState(
    val isLoading: Boolean = false,
    val currentWeather: CurrentWeather? = null,
    val forecast: List<ForecastDay> = emptyList(),
    val location: String = "",
    val error: String? = null,
    val selectedForecast: ForecastDay? = null,
    val selectedHourlyForecasts: List<HourlyForecast> = emptyList()
)