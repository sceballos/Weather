package com.jaegersoft.weather.ui.main.fragments.search

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jaegersoft.weather.data.response.Forecast
import com.jaegersoft.weather.data.response.WeatherAPIResponse
import com.jaegersoft.weather.repository.WeatherRepository
import com.jaegersoft.weather.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.collections.HashMap
import kotlin.random.Random.Default.nextInt


class
WeatherViewModel
@ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState = MutableLiveData<DataState<WeatherAPIResponse>>()
    val dataState : LiveData<DataState<WeatherAPIResponse>>
        get() = _dataState

    @ExperimentalCoroutinesApi
    fun setStateEvent(dataState: WeatherSearchStateEvent, query: String) {
        viewModelScope.launch {
            when(dataState) {
                is WeatherSearchStateEvent.GetCurrent -> {
                    Log.e("TAG", "GetCurrent")
                    weatherRepository.getCurrent(query).onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
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

    fun fillForecastDummyData(response: WeatherAPIResponse) {

        when (_dataState.value) {
            is DataState.Success<WeatherAPIResponse> -> {
                val resultMap = HashMap<String, Forecast>()

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
    object GetCurrent : WeatherSearchStateEvent()
    object GetForecast : WeatherSearchStateEvent()
}