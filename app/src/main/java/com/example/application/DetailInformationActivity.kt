package com.example.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_detail_information.*

class DetailInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_information)
        val id = intent.getIntExtra("CITY_ID", 802)
        lifecycleScope.launchWhenCreated {
            val weather = ApiFactory.weatherApi.getWeatherById(id)
            bindData(weather)
        }
    }

    private fun bindData(weather: Weather) {

        tv_city_info.text = weather.name
        tv_temp_info.text = weather.main.temp.toString()
        tv_description.text = weather.weather[0].description
        tv_windSpeed_info.text = weather.wind.speed.toString()
        tv_humidity.text = weather.main.humidity.toString()
        val deg = weather.wind.deg
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