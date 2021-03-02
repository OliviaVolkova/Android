package com.example.application.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.application.data.entities.NearCity
import com.example.application.data.entities.WeatherInCity

@Database(
        entities = [NearCity::class, WeatherInCity::class],
        version = 1
)
abstract class WeatherDB : RoomDatabase() {
    abstract val weatherDAO: WeatherDAO
    companion object {
        private var instance: WeatherDB? = null
        fun getInstance(context: Context): WeatherDB {
            return instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDB::class.java,
                    "weather_db"
            ).build().also {
                instance = it
            }
        }
    }
}