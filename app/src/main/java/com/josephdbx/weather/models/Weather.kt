package com.josephdbx.weather.models

class Weather constructor(id: Int, main: String, description: String, icon: String) {
    var id: Int = 0
    var main: String = ""
    var description: String = ""
    var icon: String = ""

    init {
        this.id = id
        this.main = main
        this.description = description
        this.icon = icon
    }
}