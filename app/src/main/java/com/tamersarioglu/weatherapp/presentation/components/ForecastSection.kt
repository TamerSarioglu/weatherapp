package com.tamersarioglu.weatherapp.presentation.components

import androidx.annotation.OpenForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tamersarioglu.weatherapp.domain.models.ForecastDay
import com.tamersarioglu.weatherapp.presentation.extentions.toFormattedDate

@Composable
fun ForecastSection(
    forecast: List<ForecastDay>,
    onForecastClick: (ForecastDay) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp)),
        color = Color.White.copy(alpha = 0.15f)
    ) {
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(forecast.size) { index ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ForecastItem(
                        day = forecast[index].date.toFormattedDate(),
                        temperature = "Avg: ${forecast[index].avgTemp}°",
                        min = "${forecast[index].minTemp}°",
                        max = "${forecast[index].maxTemp}°",
                        onClick = { onForecastClick(forecast[index]) }
                    )

                    if (index < forecast.size - 1) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .width(1.dp)
                                .background(Color.White.copy(alpha = 0.3f))
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ForecastItem(
    day: String,
    min:String,
    max: String,
    temperature: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = day,
            color = Color.White,
            fontSize = 14.sp
        )
        Text(
            text = "$min/$max",
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.width(60.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = temperature,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview()
@Composable
fun ForecastItemPreview() {
    ForecastItem(
        day = "Monday",
        min = "10°",
        max = "20°",
        temperature = "Avg: 15°",
        onClick = {}
    )
}
