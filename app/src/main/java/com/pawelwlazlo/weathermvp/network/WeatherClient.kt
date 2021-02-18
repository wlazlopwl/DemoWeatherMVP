package com.pawelwlazlo.weathermvp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClient {

    const val BASE_URL = "http://api.openweathermap.org/"

    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    val service: Service = retrofit.create(Service::class.java)
}