package com.tamersarioglu.weatherapp.domain.models

data class Astronomy(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    val moonPhase: String,
    val moonIllumination: Int
)