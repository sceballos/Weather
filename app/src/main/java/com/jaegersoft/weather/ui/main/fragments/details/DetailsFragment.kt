package com.jaegersoft.weather.ui.main.fragments.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jaegersoft.weather.R
import com.jaegersoft.weather.data.response.Forecast
import com.jaegersoft.weather.data.response.WeatherAPIResponse
import com.jaegersoft.weather.databinding.DetailsFragmentBinding
import com.jaegersoft.weather.ui.MainActivity
import com.jaegersoft.weather.ui.main.fragments.details.recyclerview.ForecastAdapter
import com.jaegersoft.weather.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var mainActivity: MainActivity
    private lateinit var binding : DetailsFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false)

        setupRecyclerView()


        mainActivity.viewModel.dataState.value.let {
            when (it) {
                is DataState.Success<WeatherAPIResponse> -> {
                    updateUI(it.data)
                }

                is DataState.Error -> {
                    Log.e("TAG", "details : error", )

                }

                is DataState.Loading -> {
                    Log.e("TAG", "details : loading", )
                }

                null -> {
                    Log.e("TAG", "details : null", )
                }
            }
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.forecastRv.layoutManager = LinearLayoutManager(context)
        binding.forecastRv.setHasFixedSize(false)
    }

    private fun updateUI(apiResponse: WeatherAPIResponse) {
        binding.cityTv.text = "${apiResponse.location.name}, ${apiResponse.location.country}"
        Glide.with(this)
            .load(apiResponse.current.weatherIcons[0])
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.currentWeatherIv)
        binding.currentTempTv.text = "${apiResponse.current.temperature.toString()} °"

        val adapter = apiResponse.forecastMap?.toList()?.let {
            ForecastAdapter(it, context) { item -> openForecast(item)
            }
        }
        binding.forecastRv.adapter = adapter
        binding.invalidateAll()
    }

    private fun openForecast(forecast : Pair<String, Forecast>) {

    }
}