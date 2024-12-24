package com.tamersarioglu.weatherapp.data.remotedto

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("data")
    val data: WeatherDataDto
)

data class WeatherDataDto(
    @SerializedName("current_condition")
    val currentCondition: List<CurrentConditionDto>,
    @SerializedName("weather")
    val forecast: List<ForecastDto>,
    @SerializedName("ClimateAverages")
    val climateAverages: List<ClimateAveragesDto>,
    @SerializedName("request")
    val request: List<RequestDto>
)

data class CurrentConditionDto(
    @SerializedName("observation_time")
    val observationTime: String,
    @SerializedName("temp_C")
    val tempC: String,
    @SerializedName("temp_F")
    val tempF: String,
    @SerializedName("weatherCode")
    val weatherCode: String,
    @SerializedName("weatherIconUrl")
    val weatherIconUrl: List<WeatherIconUrlDto>,
    @SerializedName("weatherDesc")
    val weatherDesc: List<WeatherDescDto>,
    @SerializedName("windspeedMiles")
    val windspeedMiles: String,
    @SerializedName("windspeedKmph")
    val windSpeed: String,
    @SerializedName("winddirDegree")
    val winddirDegree: String,
    @SerializedName("winddir16Point")
    val winddir16Point: String,
    @SerializedName("precipMM")
    val precipMM: String,
    @SerializedName("precipInches")
    val precipInches: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("visibility")
    val visibility: String,
    @SerializedName("visibilityMiles")
    val visibilityMiles: String,
    @SerializedName("pressure")
    val pressure: String,
    @SerializedName("pressureInches")
    val pressureInches: String,
    @SerializedName("cloudcover")
    val cloudcover: String,
    @SerializedName("FeelsLikeC")
    val feelsLike: String,
    @SerializedName("FeelsLikeF")
    val feelsLikeF: String,
    @SerializedName("uvIndex")
    val uvIndex: String
)

data class WeatherDescDto(
    @SerializedName("value")
    val value: String
)

data class WeatherIconUrlDto(
    @SerializedName("value")
    val value: String
)

data class ForecastDto(
    @SerializedName("date")
    val date: String,
    @SerializedName("astronomy")
    val astronomy: List<AstronomyDto>,
    @SerializedName("maxtempC")
    val maxTemp: String,
    @SerializedName("mintempC")
    val minTemp: String,
    @SerializedName("avgtempC")
    val avgTempC: String,
    @SerializedName("totalSnow_cm")
    val totalSnowCm: String,
    @SerializedName("sunHour")
    val sunHour: String,
    @SerializedName("uvIndex")
    val uvIndex: String,
    @SerializedName("hourly")
    val hourly: List<HourlyForecastDto>
)

data class AstronomyDto(
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String,
    @SerializedName("moonrise")
    val moonrise: String,
    @SerializedName("moonset")
    val moonset: String,
    @SerializedName("moon_phase")
    val moonPhase: String,
    @SerializedName("moon_illumination")
    val moonIllumination: String
)

data class HourlyForecastDto(
    @SerializedName("time")
    val time: String,
    @SerializedName("tempC")
    val tempC: String,
    @SerializedName("tempF")
    val tempF: String,
    @SerializedName("windspeedKmph")
    val windspeedKmph: String,
    @SerializedName("weatherCode")
    val weatherCode: String,
    @SerializedName("weatherIconUrl")
    val weatherIconUrl: List<WeatherIconUrlDto>,
    @SerializedName("weatherDesc")
    val weatherDesc: List<WeatherDescDto>,
    @SerializedName("precipMM")
    val precipMM: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("visibility")
    val visibility: String,
    @SerializedName("pressure")
    val pressure: String,
    @SerializedName("cloudcover")
    val cloudcover: String,
    @SerializedName("FeelsLikeC")
    val feelsLikeC: String,
    @SerializedName("chanceofrain")
    val chanceOfRain: String
)

data class ClimateAveragesDto(
    @SerializedName("month")
    val month: List<MonthlyClimateDto>
)

data class MonthlyClimateDto(
    @SerializedName("index")
    val index: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("avgMinTemp")
    val avgMinTemp: String,
    @SerializedName("avgMinTemp_F")
    val avgMinTempF: String,
    @SerializedName("absMaxTemp")
    val absMaxTemp: String,
    @SerializedName("absMaxTemp_F")
    val absMaxTempF: String,
    @SerializedName("avgDailyRainfall")
    val avgDailyRainfall: String
)

data class RequestDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("query")
    val query: String
)