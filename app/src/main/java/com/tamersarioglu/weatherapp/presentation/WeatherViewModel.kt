package com.tamersarioglu.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamersarioglu.weatherapp.domain.models.ForecastDay
import com.tamersarioglu.weatherapp.domain.usecases.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    fun getWeather(location: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getWeatherUseCase(location)
                .onSuccess { weatherInfo ->
                    val todayHourlyForecasts = weatherInfo.forecast.firstOrNull()?.hourlyForecasts.orEmpty()
                    val forecastWithoutToday = weatherInfo.forecast.drop(1)
                    _state.update {
                        it.copy(
                            isLoading = false,
                            currentWeather = weatherInfo.currentWeather,
                            forecast = forecastWithoutToday,
                            location = location,
                            selectedHourlyForecasts = todayHourlyForecasts,
                            selectedForecast = weatherInfo.forecast.firstOrNull()
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.message ?: "Unknown error occurred"
                        )
                    }
                }
        }
    }

    fun updateSelectedForecast(selectedDay: ForecastDay) {
        _state.update { currentState ->
            currentState.copy(
                selectedForecast = selectedDay,
                selectedHourlyForecasts = selectedDay.hourlyForecasts
            )
        }
    }
}