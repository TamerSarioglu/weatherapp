package com.tamersarioglu.weatherapp.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tamersarioglu.weatherapp.domain.models.HourlyForecast
import com.tamersarioglu.weatherapp.presentation.WeatherColors
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun HourlyForecastSection(
    hourlyForecasts: List<HourlyForecast>,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(24.dp),
        color = Color.White.copy(alpha = 0.15f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Hourly Forecast",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                val temperatures = remember(hourlyForecasts) {
                    hourlyForecasts.map { it.temperature }.distinct().sorted()
                }
                
                Column(
                    modifier = Modifier
                        .width(30.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    temperatures.asReversed().forEach { temp ->
                        Text(
                            text = "${temp}°",
                            color = Color.White,
                            fontSize = 10.sp,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    ) {
                        val width = size.width
                        val height = size.height
                        val spaceBetweenPoints = width / (hourlyForecasts.size - 1).coerceAtLeast(1)
                        val minTemp = temperatures.first()
                        val maxTemp = temperatures.last()
                        val range = (maxTemp - minTemp).coerceAtLeast(1)

                        val path = Path()
                        hourlyForecasts.forEachIndexed { index, forecast ->
                            val x = index * spaceBetweenPoints
                            val y = height - (forecast.temperature - minTemp) * height / range

                            if (index == 0) {
                                path.moveTo(x, y)
                            } else {
                                path.lineTo(x, y)
                            }

                            // Draw temperature points
                            drawCircle(
                                color = WeatherColors.AccentColor,
                                radius = 4.dp.toPx(),
                                center = Offset(x, y)
                            )
                        }

                        drawPath(
                            path = path,
                            color = WeatherColors.AccentColor,
                            style = Stroke(
                                width = 2.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        hourlyForecasts.forEach { forecast ->
                            Text(
                                text = forecast.time.format(DateTimeFormatter.ofPattern("HH:mm")),
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyForecastSectionPreview() {

    val localTime = LocalTime.now()

    HourlyForecastSection(
        hourlyForecasts = List(24) {
            HourlyForecast(
                time = localTime.plusHours(it.toLong()),
                temperature = (0..30).random(),
                windSpeed = (0..30).random().toFloat(),
                temperatureF = (0..30).random(),
                humidity = (0..100).random(),
                description = "Clear",
                weatherCode = "113",
                weatherIconUrl = "https://www.weatherbit.io/static/img/icons/c01d.png",
                precipitation = (0..100).random().toFloat(),
                visibility = (0..100).random(),
                pressure = (0..100).random(),
                cloudCover = (0..100).random(),
                feelsLike = (0..30).random(),
                chanceOfRain = 2


            )
        }
    )
}