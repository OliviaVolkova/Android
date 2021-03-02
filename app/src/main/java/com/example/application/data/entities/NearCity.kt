package com.example.application.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NearCity(
        @PrimaryKey(autoGenerate = false)
        var id: Int,
        @Embedded(prefix = "city_")
        var weatherInCity: WeatherInCity
)