package com.tamersarioglu.weatherapp.di

import com.tamersarioglu.weatherapp.data.WeatherApi
import com.tamersarioglu.weatherapp.domain.repository.WeatherRepository
import com.tamersarioglu.weatherapp.domain.repositoryimpl.WeatherRepositoryImpl
import com.tamersarioglu.weatherapp.mappers.WeatherMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherApi,
        mapper: WeatherMapper
    ): WeatherRepository {
        return WeatherRepositoryImpl(api, mapper)
    }

    @Provides
    @Singleton
    fun provideWeatherMapper(): WeatherMapper {
        return WeatherMapper()
    }
}