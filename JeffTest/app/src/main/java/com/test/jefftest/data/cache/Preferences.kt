package com.test.jefftest.data.cache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.test.jefftest.data.model.Cities
import com.test.jefftest.data.model.CitiesCache
import com.test.jefftest.data.model.CitiesSearch
import com.test.jefftest.data.model.City


class Preferences(context: Context){

    private val prefs: SharedPreferences = context.getSharedPreferences("TEST", 0)
    private val CITIESCACHE = "cities_cache"
    var listCities: CitiesCache?
        get(){
            if (prefs.contains(CITIESCACHE)){

                return try {
                    Gson().fromJson<CitiesCache>(
                            prefs.getString(CITIESCACHE, "")
                            , CitiesCache::class.java)
                }catch (exc: Exception){
                    null
                }

            }
            return null
        }
        set(value) = prefs.edit().putString(CITIESCACHE, Gson().toJson(value)).apply()


    fun addCity(city: City){
        var array = ArrayList<City>()
        if (prefs.contains(CITIESCACHE)){
            listCities?.let {
                array = ArrayList(it.objects)
            }
        }
        var isInList = false
        array.forEach {
            if (it.name == city.name && it.countryName == city.countryName) isInList = true
        }

        if (!isInList) {
            array.add(city)
            listCities = CitiesCache(array)
        }
    }

}





