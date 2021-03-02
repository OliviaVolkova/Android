package com.example.application.presentation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.application.data.retrofit.ApiFactory
import com.example.application.R
import com.example.application.data.entities.Weather
import com.example.application.data.entities.WeatherInCity
import com.example.application.data.repositories.WeatherRepositoryImpl
import com.example.application.data.room.WeatherDB
import com.example.application.domain.FindCityUseCase
import kotlinx.android.synthetic.main.activity_detail_information.*
import kotlinx.coroutines.Dispatchers

class DetailInformationActivity : AppCompatActivity() {

    private lateinit var useCase : FindCityUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_information)
        val id = intent.getIntExtra("CITY_ID", 1)
        useCase = FindCityUseCase(WeatherRepositoryImpl(ApiFactory.weatherApi, WeatherDB.getInstance(applicationContext).weatherDAO), Dispatchers.IO)
        lifecycleScope.launchWhenCreated {
            val weather = useCase.getWeatherById(id)
            bindData(weather)
        }
    }

    private fun bindData(weather: WeatherInCity) {

        tv_city_info.text = weather.name
        tv_temp_info.text = weather.temperature.toString()
        tv_description_info.text = weather.description
        tv_windSpeed_info.text = weather.windSpeed.toString()
        tv_humidity_info.text = weather.humidity.toString()
        val deg = weather.windDegree
        tv_wind_direction_info.text = when (deg) {
            in 0..22 -> "С"
            in 23..67 -> "СВ"
            in 68..112 -> "В"
            in 113..157 -> "ЮВ"
            in 158..202 -> "Ю"
            in 203..247 -> "ЮЗ"
            in 248..292 -> "З"
            in 293..337 -> "СЗ"
            in 337..361 -> "С"
            else -> "не существует"
        }


    }


}