package com.pawelwlazlo.weathermvp.model

import android.util.Log
import com.pawelwlazlo.weathermvp.Contract
import com.pawelwlazlo.weathermvp.model.data.WeatherResponse
import com.pawelwlazlo.weathermvp.network.Service
import com.pawelwlazlo.weathermvp.network.WeatherClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherModel : Contract.Model {

    // paste API KEY from openweathermap here for "Current weather data"
    val API_KEY = ""

    override fun getWeatherData(city: String, onFinishListener: Contract.Model.OnFinishedListener) {
        WeatherClient.service.getWeather(city, API_KEY).enqueue(object : Callback<WeatherResponse>
        {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful){
                    onFinishListener.setData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

                onFinishListener.isFailure(t.message.toString())
            }

        }
        )
    }


}