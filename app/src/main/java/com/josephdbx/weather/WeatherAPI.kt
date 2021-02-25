package com.josephdbx.weather

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class WeatherAPI constructor(context: Context) {
    companion object {
        const val API_KEY = "c9ad7ddba5f278cb3867b29fad57cb4f"

        @Volatile
        private var INSTANCE: WeatherAPI? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: WeatherAPI(context).also {
                    INSTANCE = it
                }
            }

        fun currentCityWeather(
            context: Context,
            cityId: String?,
            onResponse: (response: String?) -> Unit,
            onError: (response: String?) -> Unit
        ) {
            val stringRequest = StringRequest(Request.Method.GET,
                "https://api.openweathermap.org/data/2.5/weather?units=metric&lang=es&id=$cityId&appid=$API_KEY",
                { response ->
                    onResponse(response)
                },
                { error ->
                    onError(error.toString())
                })
            WeatherAPI.getInstance(context).addToRequestQueue(stringRequest)
        }
    }

    val imageLoader: ImageLoader by lazy {
        ImageLoader(requestQueue,
            object : ImageLoader.ImageCache {
                private val cache = LruCache<String, Bitmap>(20)
                override fun getBitmap(url: String): Bitmap {
                    return cache.get(url)
                }

                override fun putBitmap(url: String, bitmap: Bitmap) {
                    cache.put(url, bitmap)
                }
            })
    }

    val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}