package com.jaegersoft.weather.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaegersoft.weather.R
import com.jaegersoft.weather.data.response.WeatherAPIResponse
import com.jaegersoft.weather.databinding.ActivityMainBinding
import com.jaegersoft.weather.ui.main.DetailsActivity
import com.jaegersoft.weather.ui.main.misc.search.recyclerview.RecentSearchAdapter
import com.jaegersoft.weather.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("MainActivity", "onCreateView: creating")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupRecyclerView()

        binding.searchBtn.setOnClickListener {

            if (binding.cityEt.text.isEmpty()) {
                Toast.makeText(this, "Please input the name of a city", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.setStateEvent(
                WeatherSearchStateEvent.GetForecast,
                binding.cityEt.text.toString()
            )
        }

        viewModel.dataState.observe(this, { dataState ->
            Log.e("MainActivity", "onCreateView: observe dataState updated")
            when (dataState) {
                is DataState.Success<WeatherAPIResponse> -> {
                    Log.e("MainActivity", "onCreateView: fragment transition to details")
                    viewModel.fillForecastDummyData()
                    viewModel.addRecentSearch(dataState.data)
                    showProgressBar(false)
                    startDetailsActivity(dataState.data)
                }
                is DataState.Error -> {
                    Log.e("MainActivity", "onCreateView: ERROR getting data")
                    showProgressBar(false)
                    Toast.makeText(this, dataState.exception, Toast.LENGTH_SHORT).show()
                }
                DataState.Loading -> {
                    Log.e("MainActivity", "onCreateView: update ui")
                    showProgressBar(true)
                }
            }
        })

        if (viewModel.recentSearch.value?.size == 0) {
            showEmptySearchesMessage(true)
        } else {
            showEmptySearchesMessage(false)
        }

        viewModel.recentSearch.observe(this, { data ->
            Log.e("MainActivity", "onCreate: new data", )
            val adapter = RecentSearchAdapter(data, this) { item ->
                startDetailsActivity(item)
            }
            binding.recentSearchRv.adapter = adapter

            if (viewModel.recentSearch.value?.size == 0) {
                showEmptySearchesMessage(true)
            } else {
                showEmptySearchesMessage(false)
            }

            binding.invalidateAll()
        })
    }


    private fun showEmptySearchesMessage(show : Boolean) {
        if (show) {
            binding.recentSearchRv.visibility = View.INVISIBLE
            binding.emptySearchesTv.visibility = View.VISIBLE
        } else {
            binding.recentSearchRv.visibility = View.VISIBLE
            binding.emptySearchesTv.visibility = View.INVISIBLE
        }
    }

    private fun showProgressBar(show : Boolean) {
        if (show) {
            binding.weatherRequestPb.visibility = View.VISIBLE
            binding.searchBtn.visibility = View.INVISIBLE
        } else {
            binding.weatherRequestPb.visibility = View.INVISIBLE
            binding.searchBtn.visibility = View.VISIBLE
        }
    }

    private fun startDetailsActivity(weatherAPIResponse: WeatherAPIResponse) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("api_response", weatherAPIResponse)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        binding.recentSearchRv.layoutManager = LinearLayoutManager(this)
        binding.recentSearchRv.setHasFixedSize(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TAG", "onDestroy: MAIN", )
    }

}