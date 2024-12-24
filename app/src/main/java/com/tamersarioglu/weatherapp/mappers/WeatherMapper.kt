package com.tamersarioglu.weatherapp.mappers

import com.tamersarioglu.weatherapp.data.remotedto.AstronomyDto
import com.tamersarioglu.weatherapp.data.remotedto.ClimateAveragesDto
import com.tamersarioglu.weatherapp.data.remotedto.CurrentConditionDto
import com.tamersarioglu.weatherapp.data.remotedto.ForecastDto
import com.tamersarioglu.weatherapp.data.remotedto.HourlyForecastDto
import com.tamersarioglu.weatherapp.data.remotedto.RequestDto
import com.tamersarioglu.weatherapp.data.remotedto.WeatherResponseDto
import com.tamersarioglu.weatherapp.domain.models.Astronomy
import com.tamersarioglu.weatherapp.domain.models.CurrentWeather
import com.tamersarioglu.weatherapp.domain.models.ForecastDay
import com.tamersarioglu.weatherapp.domain.models.HourlyForecast
import com.tamersarioglu.weatherapp.domain.models.Location
import com.tamersarioglu.weatherapp.domain.models.MonthlyClimate
import com.tamersarioglu.weatherapp.domain.models.WeatherInfo
import java.time.LocalTime
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun mapToDomain(dto: WeatherResponseDto): WeatherInfo {
        val currentCondition = dto.data.currentCondition.first()

        return WeatherInfo(
            currentWeather = mapCurrentWeather(currentCondition),
            forecast = dto.data.forecast.map { mapForecastDay(it) },
            location = mapLocation(dto.data.request.first()),
            climateAverages = dto.data.climateAverages.firstOrNull()?.let { mapClimateAverages(it) }
        )
    }

    private fun mapCurrentWeather(dto: CurrentConditionDto): CurrentWeather {
        return CurrentWeather(
            observationTime = dto.observationTime,
            temperature = dto.tempC.toInt(),
            temperatureF = dto.tempF.toInt(),
            humidity = dto.humidity.toInt(),
            windSpeed = dto.windSpeed.toFloat(),
            windSpeedMiles = dto.windspeedMiles.toFloat(),
            windDirection = dto.winddir16Point,
            windDegree = dto.winddirDegree.toInt(),
            feelsLike = dto.feelsLike.toInt(),
            feelsLikeF = dto.feelsLikeF.toInt(),
            description = dto.weatherDesc.first().value,
            weatherCode = dto.weatherCode,
            weatherIconUrl = dto.weatherIconUrl.first().value,
            precipitation = dto.precipMM.toFloat(),
            visibility = dto.visibility.toInt(),
            pressure = dto.pressure.toInt(),
            cloudCover = dto.cloudcover.toInt(),
            uvIndex = dto.uvIndex.toInt()
        )
    }

    private fun mapForecastDay(dto: ForecastDto): ForecastDay {
        return ForecastDay(
            date = dto.date,
            maxTemp = dto.maxTemp.toInt(),
            minTemp = dto.minTemp.toInt(),
            avgTemp = dto.avgTempC.toInt(),
            totalSnow = dto.totalSnowCm.toFloat(),
            sunHours = dto.sunHour.toFloat(),
            uvIndex = dto.uvIndex.toInt(),
            astronomy = mapAstronomy(dto.astronomy.first()),
            hourlyForecasts = dto.hourly.map { mapHourlyForecast(it) }
        )
    }

    private fun mapAstronomy(dto: AstronomyDto): Astronomy {
        return Astronomy(
            sunrise = dto.sunrise,
            sunset = dto.sunset,
            moonrise = dto.moonrise,
            moonset = dto.moonset,
            moonPhase = dto.moonPhase,
            moonIllumination = dto.moonIllumination.toInt()
        )
    }

    private fun mapHourlyForecast(dto: HourlyForecastDto): HourlyForecast {
        return HourlyForecast(
            time = parseHourFromTime(dto.time),
            temperature = dto.tempC.toInt(),
            temperatureF = dto.tempF.toInt(),
            windSpeed = dto.windspeedKmph.toFloat(),
            description = dto.weatherDesc.first().value,
            weatherCode = dto.weatherCode,
            weatherIconUrl = dto.weatherIconUrl.first().value,
            precipitation = dto.precipMM.toFloat(),
            humidity = dto.humidity.toInt(),
            visibility = dto.visibility.toInt(),
            pressure = dto.pressure.toInt(),
            cloudCover = dto.cloudcover.toInt(),
            feelsLike = dto.feelsLikeC.toInt(),
            chanceOfRain = dto.chanceOfRain.toInt()
        )
    }

    private fun mapLocation(dto: RequestDto): Location {
        return Location(
            type = dto.type,
            query = dto.query
        )
    }

    private fun mapClimateAverages(dto: ClimateAveragesDto): List<MonthlyClimate> {
        return dto.month.map { monthDto ->
            MonthlyClimate(
                index = monthDto.index.toInt(),
                name = monthDto.name,
                avgMinTemp = monthDto.avgMinTemp.toFloat(),
                avgMinTempF = monthDto.avgMinTempF.toFloat(),
                absMaxTemp = monthDto.absMaxTemp.toFloat(),
                absMaxTempF = monthDto.absMaxTempF.toFloat(),
                avgDailyRainfall = monthDto.avgDailyRainfall.toFloat()
            )
        }
    }

    private fun parseHourFromTime(timeString: String): LocalTime {
        val hour = timeString.toInt() / 100
        return LocalTime.of(hour, 0)
    }
}