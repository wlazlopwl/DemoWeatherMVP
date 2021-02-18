package com.pawelwlazlo.weathermvp.presenter

import android.widget.EditText
import com.pawelwlazlo.weathermvp.Contract
import com.pawelwlazlo.weathermvp.model.data.WeatherResponse

class WeatherPresenter(var weatherModel: Contract.Model, var weatherView: Contract.View?): Contract.Presenter, Contract.Model.OnFinishedListener {





    override fun getWeatherData(city: String) {
        weatherView?.showProgress()
        weatherModel.getWeatherData(city, this)

    }

    override fun onDestroy() {
        weatherView = null
    }


    override fun setData(weatherResponse: WeatherResponse) {
       weatherView!!.setData(weatherResponse)
        weatherView?.hideProgress()
    }

    override fun isFailure(message: String) {
        weatherView?.showToast(message)
    }


}