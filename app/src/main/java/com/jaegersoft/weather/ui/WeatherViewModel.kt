package com.jaegersoft.weather.ui

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jaegersoft.weather.data.response.Forecast
import com.jaegersoft.weather.data.response.WeatherAPIResponse
import com.jaegersoft.weather.di.APICachePreferences
import com.jaegersoft.weather.repository.WeatherRepository
import com.jaegersoft.weather.util.DataState
import com.jaegersoft.weather.util.SingleLiveEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.function.Predicate
import kotlin.collections.HashMap


class
WeatherViewModel
@ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    private val apiCachePreferences: APICachePreferences,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recentSearch = MutableLiveData<MutableList<WeatherAPIResponse>>()
    val recentSearch : LiveData<MutableList<WeatherAPIResponse>>
        get() = _recentSearch

    private val _dataState = SingleLiveEvent<DataState<WeatherAPIResponse>>()
    val dataState : LiveData<DataState<WeatherAPIResponse>>
        get() = _dataState

    init {
        _recentSearch.value = apiCachePreferences.getCachedResponses().toMutableList()
    }

    @ExperimentalCoroutinesApi
    fun setStateEvent(dataState: WeatherSearchStateEvent, query: String) {
        viewModelScope.launch {
            when(dataState) {
                is WeatherSearchStateEvent.GetForecast -> {
                    Log.e("TAG", "GetForecast")
                    weatherRepository.getForecast(query).onEach { dataState ->
                        Log.e("TAG", "setStateEvent: viewmodel INSIDE")
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    fun addRecentSearch(response : WeatherAPIResponse) {
        val temp = _recentSearch.value

        temp?.let {
            it.add(0, response)
            _recentSearch.value = it
            apiCachePreferences.setCachedResponses(it.toList())
        }
    }

    fun fillForecastDummyData() {
        when (_dataState.value) {
            is DataState.Success<WeatherAPIResponse> -> {

                val resultMap = HashMap<String, Forecast>()

                if ((_dataState.value as DataState.Success<WeatherAPIResponse>).data.forecastMap == null) {
                    return
                }

                for (i in 1..6) {
                    val baseDate = LocalDate.parse(
                        (_dataState.value as DataState.Success<WeatherAPIResponse>).data.forecastMap?.toList()
                            ?.get(0)?.first.toString()
                    )

                    val dummyIndexDate = baseDate.plusDays(i.toLong())
                    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE")
                    val dummyDateString: String = dummyIndexDate.format(formatter)

                    resultMap.put(
                        i.toString(), Forecast(
                            dummyDateString,
                            0,
                            0.0,
                            (1..15).random().toDouble(),
                            0.0,
                            0.0,
                            0.0,
                            0.0
                        )
                    )
                }

                (_dataState.value as DataState.Success<WeatherAPIResponse>).data.forecastMap =
                    resultMap
            }
        }
    }
}

sealed class WeatherSearchStateEvent {
    object GetForecast : WeatherSearchStateEvent()
}