package com.pawelwlazlo.weathermvp.view

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.pawelwlazlo.weathermvp.Contract
import com.pawelwlazlo.weathermvp.R

import com.pawelwlazlo.weathermvp.model.WeatherModel
import com.pawelwlazlo.weathermvp.model.data.WeatherResponse
import com.pawelwlazlo.weathermvp.presenter.WeatherPresenter

class MainActivity : AppCompatActivity(), Contract.View {
    lateinit var weatherPresenter: Contract.Presenter
    lateinit var progressBar: ProgressBar
    lateinit var button: Button
    lateinit var editTextTextPersonName2: EditText

    lateinit var currentPressure: TextView
    lateinit var currentHumidity: TextView
    lateinit var currentTemp: TextView
    lateinit var currentFeelsLike: TextView
    lateinit var tempMax:TextView
    lateinit var tempMin:TextView
    lateinit var card :CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherPresenter = WeatherPresenter(WeatherModel(), this)


        progressBar = findViewById(R.id.progressBar)
        button = findViewById(R.id.button)
        currentPressure = findViewById(R.id.current_pressure)
        currentHumidity = findViewById(R.id.current_humidity)
        currentTemp = findViewById(R.id.current_temp)
        currentFeelsLike = findViewById(R.id.current_feels_like)
        tempMax = findViewById(R.id.temp_max)
        tempMin = findViewById(R.id.temp_min)
        card = findViewById(R.id.cardView)
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2)

        weatherPresenter = WeatherPresenter(WeatherModel(), this)

        button.setOnClickListener {
            if (editTextTextPersonName2.length() > 2) {
                weatherPresenter.getWeatherData(editTextTextPersonName2.text.toString())
            } else {
                showToast("Enter city")
            }
        }

    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
        card.visibility=View.VISIBLE
    }

    override fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }

    override fun setData(weatherResponse: WeatherResponse) {
    weatherResponse.main.let {
        currentPressure.text=it.pressure.toString()+ " hPa"
        currentHumidity.text=it.humidity.toString()+ "%"
        currentTemp.text=it.temp.toString() +" "+ 0x00B0.toChar() + "C"
        currentFeelsLike.text=it.feels_like.toString() +" "+ 0x00B0.toChar() + "C"
        tempMax.text=it.temp_max.toString()+" "+ 0x00B0.toChar() + "C"
        tempMin.text=it.temp_min.toString()+" "+ 0x00B0.toChar() + "C"
    }
    }


    override fun onDestroy() {
        super.onDestroy()
        weatherPresenter.onDestroy()
    }
}