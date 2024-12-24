package com.tamersarioglu.weatherapp.data

import com.tamersarioglu.weatherapp.BuildConfig
import com.tamersarioglu.weatherapp.data.remotedto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather.ashx")
    suspend fun getWeather(
        @Query("q") location: String,
        @Query("format") format: String = "json",
        @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("num_of_days") days: Int = 6
    ): WeatherResponseDto
}