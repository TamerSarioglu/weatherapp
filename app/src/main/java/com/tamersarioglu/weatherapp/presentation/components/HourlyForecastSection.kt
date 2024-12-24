package com.tamersarioglu.weatherapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tamersarioglu.weatherapp.domain.models.HourlyForecast
import java.time.format.DateTimeFormatter

@Composable
fun HourlyForecastSection(
    hourlyForecasts: List<HourlyForecast>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "Hourly Forecast",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(hourlyForecasts) { forecast ->
                HourlyForecastItem(forecast = forecast)
            }
        }
    }
}

@Composable
private fun HourlyForecastItem(
    forecast: HourlyForecast,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.1f))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Time
        Text(
            text = forecast.time.format(DateTimeFormatter.ofPattern("HH:mm")),
            color = Color.White,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Temperature
        Text(
            text = "${forecast.temperature}°",
            color = Color.White,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Description
        Text(
            text = forecast.description,
            color = Color.White,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            maxLines = 1
        )

        // Chance of Rain
        Text(
            text = "${forecast.chanceOfRain}%",
            color = Color.Cyan,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}