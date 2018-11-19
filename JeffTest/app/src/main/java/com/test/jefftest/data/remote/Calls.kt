package com.test.jefftest.data.remote

import com.test.jefftest.data.model.Cities
import com.test.jefftest.data.model.Meteo

import retrofit2.Call
import retrofit2.http.*


interface Calls {

    /*CITIES*/

    @Headers("Content-Type: application/json")
    @GET("searchJSON")
    fun getCities(@Query("q") id : String, @Query("username") user : String, @Query("maxRows") max : Int, @Query("style") style : String ): Call<Cities>


    @Headers("Content-Type: application/json")
    @GET("weatherJSON")
    fun getMeteo(@Query("north") north : Double, @Query("south") south : Double, @Query("east") east : Double, @Query("west") west : Double,  @Query("username") user : String, @Query("style") style : String ): Call<Meteo>


}