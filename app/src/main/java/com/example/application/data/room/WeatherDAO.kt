package com.example.application.data.room

import androidx.room.*
import com.example.application.data.entities.NearCity
import com.example.application.data.entities.WeatherInCity

@Dao
interface WeatherDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherInCity: WeatherInCity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeathers(list: List<WeatherInCity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNearCity(nearCity: NearCity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNearCities(list: List<NearCity>)

    @Transaction
    @Query("SELECT * FROM NearCity LIMIT 10")
    suspend fun getNearCities(): List<NearCity>

    @Transaction
    @Query("SELECT * FROM WeatherInCity where name = :cityName")
    suspend fun getWeatherByName(cityName: String): WeatherInCity

    @Transaction
    @Query("SELECT * FROM WeatherInCity where id = :id")
    suspend fun getWeatherById(id: Int): WeatherInCity
}