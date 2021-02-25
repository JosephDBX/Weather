package com.josephdbx.weather.models

class City constructor(
    coord: Coord,
    weather: ArrayList<Weather>,
    base: String,
    main: Main,
    visibility: String,
    wind: Wind,
    clouds: Clouds,
    dt: Long,
    sys: Sys,
    timezone: Long,
    id: String,
    name: String,
    cod: Int
) {
    var coord: Coord? = null
    var weather: ArrayList<Weather>? = null
    var base: String = ""
    var main: Main? = null
    var visibility: String = ""
    var wind: Wind? = null
    var clouds: Clouds? = null
    var dt: Long = 0
    var sys: Sys? = null
    var timezone: Long = 0
    var id: String = ""
    var name: String = ""
    var cod: Int = 0

    init {
        this.coord = coord
        this.weather = weather
        this.base = base
        this.main = main
        this.visibility = visibility
        this.wind = wind
        this.clouds = clouds
        this.dt = dt
        this.sys = sys
        this.timezone = timezone
        this.id = id
        this.name = name
        this.cod = cod
    }
}