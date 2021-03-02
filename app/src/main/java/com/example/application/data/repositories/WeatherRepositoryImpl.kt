package com.example.application.data.repositories

import android.util.Log
import com.example.application.data.entities.NearCity
import com.example.application.data.entities.Weather
import com.example.application.data.entities.WeatherInCity
import com.example.application.data.entities.WeatherList
import com.example.application.data.retrofit.WeatherApi
import com.example.application.data.room.WeatherDAO

class WeatherRepositoryImpl(
        private val weatherApi: WeatherApi,
        private val weatherDAO: WeatherDAO
) : WeatherRepository {
    override suspend fun getWeatherByName(cityName: String): WeatherInCity {
        try {
            weatherApi.getWeatherByName(cityName).also { weather ->
                return WeatherInCity(
                        weather.id,
                        weather.name,
                        weather.main.temp,
                        weather.innerWeather[0].description,
                        weather.wind.speed,
                        weather.wind.deg,
                        weather.main.humidity).also {
                    weatherDAO.insertWeather(it)
                }
            }
        } catch (e: Exception) {
            Log.d("OLIVIA",e.message.toString())
            return weatherDAO.getWeatherByName(cityName)
        }
    }

    override suspend fun getWeatherById(id: Int): WeatherInCity {
        try {
            weatherApi.getWeatherById(id).also { weather ->
                return WeatherInCity(
                        weather.id,
                        weather.name,
                        weather.main.temp,
                        weather.innerWeather[0].description,
                        weather.wind.speed,
                        weather.wind.deg,
                        weather.main.humidity).also {
                    weatherDAO.insertWeather(it)
                }
            }
        }
        catch (e: Exception){
            Log.d("OLIVIA",e.message.toString())
            return weatherDAO.getWeatherById(id)
        }
    }

    override suspend fun getWeatherByGeo(latitude: Double, longitude: Double, count: Int): List<WeatherInCity> {
        return try {
            weatherApi.getWeatherByGeo(latitude, longitude, count).list.map { weather ->
                WeatherInCity(
                        weather.id,
                        weather.name,
                        weather.main.temp,
                        weather.innerWeather[0].description,
                        weather.wind.speed,
                        weather.wind.deg,
                        weather.main.humidity
                )
            }.also {
                weatherDAO.insertWeathers(it)
                val savedList = ArrayList<NearCity>()
                for (i in 1..10) {
                    savedList.add(NearCity(i, it[i-1]))
                }
                weatherDAO.insertNearCities(savedList)
            }
        }
        catch (e: Exception){
            Log.d("OLIVIA",e.message.toString())
            return weatherDAO.getNearCities().map {
                it.weatherInCity
            }
        }
    }

}
