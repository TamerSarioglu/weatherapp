# Weather App

A modern weather application built with Jetpack Compose that provides detailed weather information and forecasts.

## Features

- Current weather conditions
- Hourly forecast with interactive chart
- Daily forecast for multiple days
- Temperature, humidity, wind speed, and "feels like" metrics
- Dynamic background colors based on weather conditions
- City search functionality
- Save and quick-access favorite cities
- Clean and modern UI with smooth animations

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Dependency Injection**: Hilt
- **Networking**: Retrofit
- **JSON Parsing**: Gson
- **Image Loading**: Coil
- **Async Operations**: Kotlin Coroutines & Flow
- **Charts**: Custom Canvas implementation

## Architecture

The app follows Clean Architecture principles with the following layers:

- **Presentation**: ViewModels, Compose UI components
- **Domain**: Use cases, Repository interfaces, Models
- **Data**: Repository implementations, API services, DTOs

## Setup

1. Clone the repository
2. Create a `secrets.properties` file in the root directory
3. Add your Weather API credentials:
   WEATHER_BASE_URL="your_base_url"
   WEATHER_API_KEY="your_api_key"
4. Build and run the project

## Requirements

- Android Studio Hedgehog or newer
- JDK 17
- Android 8.0 (API 26) or higher
