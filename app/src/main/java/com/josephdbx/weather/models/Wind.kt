package com.josephdbx.weather.models

class Wind constructor(speed: Float, deg: Int) {
    var speed: Float = 0f
    var deg: Int = 0

    init {
        this.speed = speed
        this.deg = deg
    }
}