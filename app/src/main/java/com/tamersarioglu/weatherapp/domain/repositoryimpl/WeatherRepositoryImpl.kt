package com.tamersarioglu.weatherapp.domain.repositoryimpl

import com.tamersarioglu.weatherapp.data.WeatherApi
import com.tamersarioglu.weatherapp.domain.models.WeatherInfo
import com.tamersarioglu.weatherapp.domain.repository.WeatherRepository
import com.tamersarioglu.weatherapp.mappers.WeatherMapper
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val mapper: WeatherMapper
) : WeatherRepository {
    override suspend fun getWeather(location: String): Result<WeatherInfo> = try {
        val response = api.getWeather(location)
        Result.success(mapper.mapToDomain(response))
    } catch (e: Exception) {
        Result.failure(e)
    }
}