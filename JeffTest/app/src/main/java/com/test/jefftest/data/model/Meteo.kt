package com.test.jefftest.data.model

data class Meteo( var weatherObservations : List<Point>)

data class Point( val lng: Double,
                   val lat: Double,
                   val clouds: String,
                   val temperature: String,
                   val humidity: Int,
                   val stationName: String,
                   val windSpeed: String)