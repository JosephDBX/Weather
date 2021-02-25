package com.josephdbx.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.josephdbx.weather.adapters.CityItem
import com.josephdbx.weather.adapters.CityListAdapter

class MainActivity : AppCompatActivity() {
    companion object {
        const val CITY_ID = "com.josephdbx.weather.CITY_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val bManagua = findViewById<Button>(R.id.bManagua)

        bManagua.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, WeatherActivity::class.java).apply {
                putExtra(CITY_ID, "3617763")
            }
            startActivity(intent)
        })

        val bGranada = findViewById<Button>(R.id.bGranada)

        bGranada.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, WeatherActivity::class.java).apply {
                putExtra(CITY_ID, "3619136")
            }
            startActivity(intent)
        })

        val cities: ArrayList<String> = ArrayList()
        val codes: ArrayList<String> = ArrayList()
        cities.add("Managua")
        codes.add("3617763")
        cities.add("Granada")
        codes.add("3619136")

        val cityList = findViewById<ListView>(R.id.cityList)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities)

        cityList.adapter = adapter

        cityList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            run {
                val intent = Intent(this, WeatherActivity::class.java).apply {
                    putExtra(CITY_ID, codes.get(position))
                }
                startActivity(intent)
            }
        }*/

        val cities: ArrayList<CityItem> = ArrayList()
        cities.add(CityItem("Managua", "3617763"))
        cities.add(CityItem("Granada", "3619136"))

        val cityList = findViewById<ListView>(R.id.cityList)

        val adapter = CityListAdapter(this, cities)

        cityList.adapter = adapter

        cityList.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                run {
                    val intent = Intent(this, WeatherActivity::class.java).apply {
                        putExtra(CITY_ID, cities.get(position).code)
                    }
                    startActivity(intent)
                }
            }
    }
}