package com.josephdbx.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.josephdbx.weather.adapters.CityItem
import com.josephdbx.weather.adapters.CityListAdapter

class MainActivity : AppCompatActivity() {
    companion object {
        const val CITY_ID = "com.josephdbx.weather.CITY_ID"
    }

    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.tbApp)
        toolbar?.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val miSearch = menu?.findItem(R.id.mi_search)
        val viewSearch = miSearch?.actionView as SearchView

        viewSearch.queryHint = getResources().getString(R.string.menu_search)

        // Used to load & release data to make search
        viewSearch.setOnQueryTextFocusChangeListener { v, hasFocus -> Log.d("HAS FOCUS", hasFocus.toString()) }

        viewSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("onQueryTextChange", newText.toString())
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.mi1 -> {
                Toast.makeText(this, "Menu Item 1", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.mi2 -> {
                Toast.makeText(this, "Menu Item 2", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.mi3 -> {
                Toast.makeText(this, "Menu Item 3", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}