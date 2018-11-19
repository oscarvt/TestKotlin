package com.test.jefftest.presentation.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.test.jefftest.data.model.*
import com.test.jefftest.data.repositories.DataWebServices
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity_ViewModel : ViewModel() {

    val meteoResult: MutableLiveData<Meteo?> = MutableLiveData()
    var webService: DataWebServices = DataWebServices()

    fun getMeteoFromCity(city: City){

        var meteoSearch = MeteoSearch(city.bbox)

        webService.getMeteo(meteoSearch).enqueue(object : Callback<Meteo> {
            override fun onResponse(call: Call<Meteo>?, response: Response<Meteo>) {

                when (response.code()) {

                    400 -> Log.d("prueba", "ERROR SEARCH CITY: {${response.raw()}")
                    200 -> {
                            meteoResult.value = response.body()

                    }
                }
            }

            override fun onFailure(call: Call<Meteo>?, t: Throwable) {
                Log.d("prueba", "ERROR")
            }
        })


    }


}