package com.tamersarioglu.weatherapp.domain.usecases

import com.tamersarioglu.weatherapp.domain.models.WeatherInfo
import com.tamersarioglu.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(location: String): Result<WeatherInfo> =
        repository.getWeather(location)
}