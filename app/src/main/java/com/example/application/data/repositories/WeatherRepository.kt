package com.example.application.data.repositories

import com.example.application.data.entities.Weather
import com.example.application.data.entities.WeatherInCity
import com.example.application.data.entities.WeatherList


interface WeatherRepository {
    suspend fun getWeatherByName(cityName: String): WeatherInCity

    suspend fun getWeatherById(id: Int): WeatherInCity

    suspend fun getWeatherByGeo(latitude : Double, longitude: Double, count: Int): List<WeatherInCity>
}