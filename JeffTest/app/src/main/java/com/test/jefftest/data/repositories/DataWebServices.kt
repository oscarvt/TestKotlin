package com.test.jefftest.data.repositories

import com.test.jefftest.data.cache.Config
import com.test.jefftest.data.model.Cities
import com.test.jefftest.data.model.CitiesSearch
import com.test.jefftest.data.remote.Calls
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by oscarvera on 7/11/17.
 */


class DataWebServices {

    private val meteoApi: Calls

    init {
        val retrofitclient = Retrofit.Builder()
                .baseUrl(Config.urlBaseMeteo)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(Client().getWebClient())
                .build()
        meteoApi = retrofitclient.create(Calls::class.java)

    }


    fun getCities(search: CitiesSearch): Call<Cities> {
        //Log.d("prueba","Authorization: ${preferences.token}")
        return meteoApi.getCities(search.q,search.username, search.maxRows)

    }
}

