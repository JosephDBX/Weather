package com.josephdbx.weather.models

class Coord constructor(lon: Float, lat: Float) {
    var lon: Float = 0f
    var lat: Float = 0f

    init {
        this.lon = lon
        this.lat = lat
    }
}