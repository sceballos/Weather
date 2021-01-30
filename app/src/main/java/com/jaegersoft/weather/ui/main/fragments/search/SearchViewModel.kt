package com.jaegersoft.weather.ui.main.fragments.search

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jaegersoft.weather.data.response.WeatherAPIResponse
import com.jaegersoft.weather.repository.WeatherRepository
import com.jaegersoft.weather.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class
SearchViewModel
@ViewModelInject constructor(private val weatherRepository: WeatherRepository,
@Assisted private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _dataState = MutableLiveData<DataState<WeatherAPIResponse>>()

    val dataState : LiveData<DataState<WeatherAPIResponse>>
        get() = _dataState

    @ExperimentalCoroutinesApi
    fun setStateEvent(dataState : WeatherSearchStateEvent, query : String) {
        viewModelScope.launch {
            when(dataState) {
                is WeatherSearchStateEvent.GetCurrent -> {
                    Log.e("TAG", "GetCurrent", )
                    weatherRepository.getCurrent(query).onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
                is WeatherSearchStateEvent.GetForecast -> {
                    Log.e("TAG", "GetForecast", )
                    weatherRepository.getForecast(query).onEach { dataState ->
                        Log.e("TAG", "setStateEvent: viewmodel INSIDE", )
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

}

sealed class WeatherSearchStateEvent {
    object GetCurrent : WeatherSearchStateEvent()
    object GetForecast : WeatherSearchStateEvent()
}