package com.test.jefftest.data.model

import com.google.gson.Gson

data class Cities (val geonames:List<City>, val totalResultsCount : Int)


data class City (
            val name :String,
            val toponymName : String,
            val bbox : Coordenates,
            val countryName : String,
            val fcodeName : String,
            val lat : Double,
            val lng : Double,
            val population : Int)


data class Coordenates(

    val east: Double,
    val south: Double,
    val north: Double,
    val west: Double

)

fun String.getCity(): City?{

    return try {
        Gson().fromJson<City>(
                this
                , City::class.java)
    }catch (exc: Exception){
        null
    }

}

fun City.toJson(): String?{

    return try {
        Gson().toJson(this)
    }catch (exc: Exception){
        null
    }

}