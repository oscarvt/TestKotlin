package com.test.jefftest.data.model

data class Cities (val geonames:List<City>, val totalResultsCount : Int)


data class City (
            val name :String,
            val toponymName : String)


data class Coordenates(

    val east: Double,
    val south: Double,
    val north: Double,
    val west: Double

)