package com.tamersarioglu.weatherapp.domain.models

data class MonthlyClimate(
    val index: Int,
    val name: String,
    val avgMinTemp: Float,
    val avgMinTempF: Float,
    val absMaxTemp: Float,
    val absMaxTempF: Float,
    val avgDailyRainfall: Float
)