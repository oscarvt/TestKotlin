package com.test.jefftest.data.remote

import com.test.jefftest.data.model.Cities

import retrofit2.Call
import retrofit2.http.*


interface Calls {

    /*CITIES*/

    @Headers("Content-Type: application/json")
    @GET("searchJSON")
    fun getCities(@Query("q") id : String, @Query("username") user : String, @Query("maxRows") max : Int ): Call<Cities>


}