package com.pawelwlazlo.weathermvp

import com.pawelwlazlo.weathermvp.model.data.WeatherResponse

interface Contract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showToast(string:String)
        fun setData(weatherResponse: WeatherResponse)
    }

    interface Model {
        fun getWeatherData(city: String, onFinishListener: OnFinishedListener)

        interface OnFinishedListener {
            fun setData(weatherResponse: WeatherResponse)
            fun isFailure(message: String)
        }
    }

    interface Presenter {
        fun getWeatherData(city: String)

        fun onDestroy()
    }
}