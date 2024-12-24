package com.tamersarioglu.weatherapp.domain.models

data class WeatherInfo(
    val currentWeather: CurrentWeather,
    val forecast: List<ForecastDay>,
    val location: Location,
    val climateAverages: List<MonthlyClimate>?
)