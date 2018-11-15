package com.test.jefftest.data.repositories

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class Client() {

    fun getWebClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

    }
}