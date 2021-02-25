package com.josephdbx.weather.models

class Sys constructor(type: Int, id: Int, country: String, sunrise: Long, sunset: Long) {
    var type: Int = 0
    var id: Int = 0
    var country: String = ""
    var sunrise: Long = 0
    var sunset: Long = 0

    init {
        this.type = type
        this.id = id
        this.country = country
        this.sunrise = sunrise
        this.sunset = sunset
    }
}