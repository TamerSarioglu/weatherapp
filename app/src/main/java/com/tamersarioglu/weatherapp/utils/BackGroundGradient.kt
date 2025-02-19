package com.tamersarioglu.weatherapp.utils

import androidx.compose.ui.graphics.Brush
import com.tamersarioglu.weatherapp.domain.models.CurrentWeather
import com.tamersarioglu.weatherapp.presentation.WeatherColors

object BackGroundGradient {

    fun getBackgroundGradient(currentWeather: CurrentWeather?): Brush {
        return when {
            currentWeather?.description?.contains("clear", ignoreCase = true) == true -> {
                val isNight = currentWeather.observationTime.let {
                    val hour = it.substringBefore(":").toIntOrNull() ?: 12
                    hour < 6 || hour > 18
                }
                if (isNight) {
                    Brush.verticalGradient(
                        colors = listOf(WeatherColors.ClearNightTop, WeatherColors.ClearNightBottom)
                    )
                } else {
                    Brush.verticalGradient(
                        colors = listOf(WeatherColors.ClearDayTop, WeatherColors.ClearDayBottom)
                    )
                }
            }
            currentWeather?.description?.contains("cloud", ignoreCase = true) == true ->
                Brush.verticalGradient(
                    colors = listOf(WeatherColors.CloudyTop, WeatherColors.CloudyBottom)
                )
            currentWeather?.description?.contains("rain", ignoreCase = true) == true ->
                Brush.verticalGradient(
                    colors = listOf(WeatherColors.RainyTop, WeatherColors.RainyBottom)
                )
            currentWeather?.description?.contains("snow", ignoreCase = true) == true ->
                Brush.verticalGradient(
                    colors = listOf(WeatherColors.SnowyTop, WeatherColors.SnowyBottom)
                )
            else ->
                Brush.verticalGradient(
                    colors = listOf(WeatherColors.ClearDayTop, WeatherColors.ClearDayBottom)
                )
        }
    }
}