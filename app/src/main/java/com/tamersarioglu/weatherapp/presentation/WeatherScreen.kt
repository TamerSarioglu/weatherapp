package com.tamersarioglu.weatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tamersarioglu.weatherapp.presentation.components.EmptyStateContent
import com.tamersarioglu.weatherapp.presentation.components.ForecastSection
import com.tamersarioglu.weatherapp.presentation.components.HourlyForecastSection
import com.tamersarioglu.weatherapp.presentation.components.SavedCityChips
import com.tamersarioglu.weatherapp.presentation.components.TopBar
import com.tamersarioglu.weatherapp.presentation.components.WeatherContent
import com.tamersarioglu.weatherapp.presentation.components.WeatherMetrics

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    val savedCities = remember { mutableStateOf(setOf<String>()) }

    val backgroundGradient = when {
        state.currentWeather?.description?.contains("clear", ignoreCase = true) == true -> {
            val isNight = state.currentWeather?.observationTime?.let {
                val hour = it.substringBefore(":").toIntOrNull() ?: 12
                hour < 6 || hour > 18
            } ?: false

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
        state.currentWeather?.description?.contains("cloud", ignoreCase = true) == true -> {
            Brush.verticalGradient(
                colors = listOf(WeatherColors.CloudyTop, WeatherColors.CloudyBottom)
            )
        }
        state.currentWeather?.description?.contains("rain", ignoreCase = true) == true -> {
            Brush.verticalGradient(
                colors = listOf(WeatherColors.RainyTop, WeatherColors.RainyBottom)
            )
        }
        state.currentWeather?.description?.contains("snow", ignoreCase = true) == true -> {
            Brush.verticalGradient(
                colors = listOf(WeatherColors.SnowyTop, WeatherColors.SnowyBottom)
            )
        }
        else -> Brush.verticalGradient(
            colors = listOf(WeatherColors.ClearDayTop, WeatherColors.ClearDayBottom)
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        if (state.currentWeather == null) {
            EmptyStateContent(
                onSearch = { city ->
                    viewModel.getWeather(city)
                    savedCities.value += city
                },
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column {
                    TopBar(
                        location = state.location,
                        onSearch = { city ->
                            viewModel.getWeather(city)
                            savedCities.value += city
                        }
                    )
                    if (savedCities.value.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        SavedCityChips(
                            cities = savedCities.value,
                            onCityClick = { city -> viewModel.getWeather(city) },
                            onRemove = { city -> savedCities.value -= city }
                        )
                    }
                }

                WeatherContent(
                    temperature = state.currentWeather?.temperature ?: 0,
                    description = state.currentWeather?.description ?: "",
                    modifier = Modifier.weight(1f),
                    observationTime = state.currentWeather?.observationTime ?: ""
                )

                WeatherMetrics(
                    humidity = state.currentWeather?.humidity ?: 0,
                    windSpeed = state.currentWeather?.windSpeed ?: 0f,
                    feelsLike = state.currentWeather?.feelsLike ?: 0
                )

                ForecastSection(
                    forecast = state.forecast,
                    onForecastClick = { selectedDay ->
                        viewModel.updateSelectedForecast(selectedDay)
                    }
                )

                HourlyForecastSection(
                    hourlyForecasts = state.selectedHourlyForecasts.ifEmpty {
                        state.forecast.firstOrNull()?.hourlyForecasts ?: emptyList()
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center),
                color = WeatherColors.ContentPrimary
            )
        }
    }
}