package com.josephdbx.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.josephdbx.weather.models.City

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val tvDegrees = findViewById<TextView>(R.id.tvDegrees)
        val tvCity = findViewById<TextView>(R.id.tvCity)
        val tvState = findViewById<TextView>(R.id.tvState)

        val cityId = intent.getStringExtra(MainActivity.CITY_ID)

        if (Network.isInternetAvailable(this)) {
            WeatherAPI.currentCityWeather(
                this,
                cityId,
                { response: String? ->
                    run {
                        val gson = Gson()
                        val city = gson.fromJson(response, City::class.java)

                        tvDegrees.text = city.main?.temp.toString() + "º"
                        tvCity.text = city.name
                        tvState.text = city.weather?.get(0)?.description
                    }
                },
                { response: String? ->
                    Toast.makeText(this, response.toString(), Toast.LENGTH_LONG).show()
                })
        } else {
            Toast.makeText(this, "No internet connection!", Toast.LENGTH_LONG).show()
        }
    }
}