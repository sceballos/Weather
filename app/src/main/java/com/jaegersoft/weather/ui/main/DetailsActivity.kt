package com.jaegersoft.weather.ui.main
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jaegersoft.weather.R
import com.jaegersoft.weather.data.response.Forecast
import com.jaegersoft.weather.data.response.WeatherAPIResponse
import com.jaegersoft.weather.databinding.ActivityDetailsBinding
import com.jaegersoft.weather.ui.WeatherViewModel
import com.jaegersoft.weather.ui.main.misc.details.recyclerview.ForecastAdapter
import com.jaegersoft.weather.util.BitmapUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        setupRecyclerView()

        val apiResponse = intent.getParcelableExtra<WeatherAPIResponse>("api_response")

        apiResponse?.let {
            updateUI(it)
        }
    }

    private fun setupRecyclerView() {
        binding.forecastRv.layoutManager = LinearLayoutManager(this)
        binding.forecastRv.setHasFixedSize(false)
    }

    private fun updateUI(apiResponse: WeatherAPIResponse) {
        binding.cityTv.text = "${apiResponse.location.name}, ${apiResponse.location.country}"
        Glide.with(this)
            .load(apiResponse.current.weatherIcons[0])
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.currentWeatherIv)

        val weatherDescription = apiResponse.current.weatherDescriptions[0].toLowerCase()
        val isDay = apiResponse.current.isDay == "yes"

        BitmapUtils.updateBackground(binding.detailsBackground ,weatherDescription, isDay)

        binding.currentTempTv.text = "${apiResponse.current.temperature.toInt()}° C"

        binding.conditionTv.text = "${apiResponse.current.weatherDescriptions[0]}"

        binding.windSpeedTv.text = "${apiResponse.current.windSpeed.toInt()} kmph"

        binding.precipitationTv.text = "${apiResponse.current.precip.toInt()} %"

        binding.cloudCoverTv.text = "${apiResponse.current.cloudcover.toInt()} %"

        if (apiResponse.forecastMap == null) {
            binding.emptyForecastTv.visibility = View.VISIBLE
            binding.forecastRv.visibility = View.GONE
            return
        }

        val adapter = apiResponse.forecastMap?.toList()?.let {
            ForecastAdapter(it, this) { item -> openForecast(item)
            }
        }
        binding.forecastRv.adapter = adapter
        binding.invalidateAll()
    }

    private fun openForecast(forecast : Pair<String, Forecast>) {
        //TODO : implement if necessary
    }
}