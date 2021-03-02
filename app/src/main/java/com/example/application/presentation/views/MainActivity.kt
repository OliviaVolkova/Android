package com.example.application.presentation.views

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.example.application.data.retrofit.ApiFactory
import com.example.application.data.entities.WeatherInCity
import com.example.application.R
import com.example.application.data.repositories.WeatherRepositoryImpl
import com.example.application.data.room.WeatherDB
import com.example.application.domain.FindCityUseCase
import com.example.application.presentation.recyclerview.CityAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var useCase : FindCityUseCase
    private lateinit var adapter : CityAdapter
    val PERMISSION_GEO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CityAdapter {
            detailInformationActivity(it)
        }
        rv_nearest_cities.adapter = adapter
        fab_update.setOnClickListener {
            permissionOnRequest()
        }
        useCase = FindCityUseCase(WeatherRepositoryImpl(ApiFactory.weatherApi, WeatherDB.getInstance(applicationContext).weatherDAO), Dispatchers.IO)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        permissionOnRequest()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        searchView =
            menu?.findItem(R.id.action_search)?.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                lifecycleScope.launch {
                    try {
                        val weather = useCase.getWeatherByName(p0 ?: "")
                        snackBar(weather.name)
                        detailInformationActivity(weather.id)
                    }
                    catch (e :Exception){
                        snackBar("Город не найден")
                    }

                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
        return true
    }

    private fun snackBar(text: String) {
        val view : View = findViewById(android.R.id.content)
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            view.windowToken,
            0
        )
    }

    private fun permissionOnRequest() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_GEO
        )
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_GEO) {
            var areGranted = true
            grantResults.forEach {
                if (it == PackageManager.PERMISSION_DENIED) {
                    areGranted = false
                }
            }
            if (areGranted) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                    if (it.result != null) {
                        showListByGeolocation(it.result.latitude, it.result.longitude)
                    } else {
                        showListByGeolocation(55.7887, 49.1221)
                        snackBar("Выбрана Казань")
                    }
                }
            }
            else {
                showListByGeolocation(55.7887, 49.1221)
                snackBar("Выбрана Казань")
            }
        }
    }

    private fun showListByGeolocation(latitude: Double, longitude: Double) {
        lifecycleScope.launch {
            val list = useCase.getWeatherByGeo(latitude, longitude, 10)
            withContext(Dispatchers.Main) {
                adapter.submitList(list.toMutableList())
            }
        }
    }

    private fun detailInformationActivity(id: Int){
        val intent = Intent(this@MainActivity, DetailInformationActivity::class.java).apply {
            putExtra("CITY_ID", id)
        }
        startActivity(intent)
    }


}