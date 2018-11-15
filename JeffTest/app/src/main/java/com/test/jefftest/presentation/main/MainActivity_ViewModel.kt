package com.test.jefftest.presentation.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.test.jefftest.data.model.Cities
import com.test.jefftest.data.model.CitiesSearch
import com.test.jefftest.data.model.City
import com.test.jefftest.data.repositories.DataWebServices
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_ViewModel : ViewModel() {

    val citiesResult: MutableLiveData<Cities?> = MutableLiveData()
    var webService: DataWebServices = DataWebServices()

    fun searchCitiesTextImput(textInput: String){

        var search =  CitiesSearch(textInput.toString())

        webService.getCities(search).enqueue(object : Callback<Cities> {
            override fun onResponse(call: Call<Cities>?, response: Response<Cities>) {

                when (response.code()) {

                    400 -> Log.d("prueba", "ERROR SEARCH CITY: {${response.raw()}")
                    200 -> {
                            citiesResult.value = response.body()

                    }
                }
            }

            override fun onFailure(call: Call<Cities>?, t: Throwable) {
                Log.d("prueba", "ERROR")
            }
        })


    }


}