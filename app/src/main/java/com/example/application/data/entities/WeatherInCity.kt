package com.example.application.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherInCity(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var name: String,
        var temperature: Double,
        var description: String,
        var windSpeed: Double,
        var windDegree: Int,
        var humidity: Int
)
