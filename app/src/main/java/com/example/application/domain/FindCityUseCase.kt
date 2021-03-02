package com.example.application.domain

import com.example.application.data.entities.Weather
import com.example.application.data.entities.WeatherInCity
import com.example.application.data.entities.WeatherList
import com.example.application.data.repositories.WeatherRepository
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FindCityUseCase(
        private val weatherRepository: WeatherRepository,
        private val context: CoroutineContext
) {
    suspend fun getWeatherByName(cityName: String): WeatherInCity = withContext(context){
        weatherRepository.getWeatherByName(cityName)
    }

    suspend fun getWeatherById(id: Int): WeatherInCity = withContext(context){
        weatherRepository.getWeatherById(id)
    }

    suspend fun getWeatherByGeo(latitude : Double, longitude: Double, count: Int): List<WeatherInCity> = withContext(context){
        weatherRepository.getWeatherByGeo(latitude, longitude, count)
    }
}