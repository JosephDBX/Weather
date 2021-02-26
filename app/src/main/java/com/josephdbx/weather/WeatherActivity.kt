package com.josephdbx.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.ShareActionProvider
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import com.google.gson.Gson
import com.josephdbx.weather.models.City

class WeatherActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null

    var city: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        toolbar = findViewById(R.id.tbApp2)
        toolbar?.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

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
                        city = gson.fromJson(response, City::class.java)

                        tvDegrees.text = city?.main?.temp.toString() + "º"
                        tvCity.text = city?.name
                        tvState.text = city?.weather?.get(0)?.description
                    }
                },
                { response: String? ->
                    Toast.makeText(this, response.toString(), Toast.LENGTH_LONG).show()
                })
        } else {
            Toast.makeText(this, "No internet connection!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_weather, menu)

        val miSearch = menu?.findItem(R.id.mi_search)
        val shareActionProvider = MenuItemCompat.getActionProvider(miSearch) as? ShareActionProvider
        shareIntent(shareActionProvider)

        return super.onCreateOptionsMenu(menu)
    }

    private fun shareIntent(shareActionProvider: ShareActionProvider?) {
        if (shareActionProvider != null) {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    city?.name + ", " + city?.main?.temp.toString() + "º, " + city?.weather?.get(0)?.description
                )
            }
            shareActionProvider.setShareIntent(intent)
        }
    }
}